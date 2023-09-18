package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.IDishService;
import org.springframework.stereotype.Service;


@Service
public class DishService extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Override
    public PageResult queryPage(DishPageQueryDTO dto) {
        Page<Dish> dishPage = new Page<>(dto.getPage(), dto.getPageSize());
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Dish::getCreateTime);
        this.page(dishPage,wrapper);
        return new PageResult(dishPage.getTotal(),dishPage.getRecords());
    }
}
