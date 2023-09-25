package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.ICategoryService;
import com.sky.service.IDishFlavorService;
import com.sky.service.IDishService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    private final ICategoryService categoryService;

    private final IDishFlavorService dishFlavorService;

    public DishServiceImpl(ICategoryService categoryService, IDishFlavorService dishFlavorService) {
        this.categoryService = categoryService;
        this.dishFlavorService = dishFlavorService;
    }

    @Override
    public PageResult queryPage(DishPageQueryDTO dto) {
        // TODO  待优化的查询方式
        Page<Dish> dishPage = new Page<>(dto.getPage(), dto.getPageSize());
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Dish::getCreateTime);
        this.page(dishPage, wrapper);
        Page<DishVO> dishVOPage = new Page<>();
        dishVOPage.setTotal(dishPage.getTotal());
        ArrayList<DishVO> dishVOS = new ArrayList<>();
        for (Dish dish : dishPage.getRecords()) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(dish, dishVO);
            LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper<>();
            categoryWrapper.eq(Category::getId, dish.getCategoryId());
            Category category = categoryService.getOne(categoryWrapper);
            dishVO.setCategoryName(category.getName());
            LambdaQueryWrapper<DishFlavor> dishFlavorWrapper = new LambdaQueryWrapper<>();
            dishFlavorWrapper.eq(DishFlavor::getDishId, dish.getId());
            List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorWrapper);
            dishVO.setFlavors(dishFlavorList);
            dishVOS.add(dishVO);
        }

        dishVOPage.setRecords(dishVOS);
        return new PageResult(dishVOPage.getTotal(), dishVOPage.getRecords());
    }


    @Override
    public void updateStatus(int status, Long id) {
        Dish dish = new Dish();
        dish.setStatus(status);
        dish.setId(id);
        this.updateById(dish);
    }

    @Transactional
    @Override
    public void deleteDish(Long[] ids) {
        for (Long id : ids) {
            LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
            dishWrapper.eq(Dish::getId,id);
            this.remove(dishWrapper);
            // 口味是一个集合，只要满足id相同就都会删除
            LambdaQueryWrapper<DishFlavor> dishFlavorWrapper = new LambdaQueryWrapper<>();
            dishFlavorWrapper.eq(DishFlavor::getDishId,id);
            dishFlavorService.remove(dishFlavorWrapper);
        }
    }
}
