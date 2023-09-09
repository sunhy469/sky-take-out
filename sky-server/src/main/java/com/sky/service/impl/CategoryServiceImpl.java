package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.CategoryBeenUsedException;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements ICategoryService {

    // 新增分类
    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(categoryDTO.getSort()!=null,Category::getSort,categoryDTO.getSort());
        Category one = this.getOne(wrapper);
        // 重复排序,排除只修改name的情况
        if (one!=null)
            throw new CategoryBeenUsedException("名称或排序已存在，请检查后重试");
        // 正确添加
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        category.setStatus(0);
        this.save(category);
    }

    // 更新分类状态
    @Override
    public void updateStatus(Integer status, Long id) {
        LambdaUpdateWrapper<Category> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Category::getStatus,status)
                .eq(Category::getId,id);
        this.update(wrapper);
    }

    // 修改分类
    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(categoryDTO.getSort()!=null,Category::getSort,categoryDTO.getSort());
        Category one = this.getOne(wrapper);
        // 重复排序,排除只修改name的情况
        if (one!=null&&one.getName().equals(categoryDTO.getName()))
            throw new CategoryBeenUsedException("名称或排序已存在，请检查后重试");
        // 正确修改
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        this.updateById(category);
    }

    // 分页查询
    @Override
    public PageResult queryPage(CategoryPageQueryDTO categoryPageQueryDTO) {
        Page<Category> categoryPage =
                new Page<>(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        // 先做判断，为空就是默认查询
        // 按分类名称查询
        wrapper.eq(categoryPageQueryDTO.getName()!=null,
                Category::getName,categoryPageQueryDTO.getName());
        // 按分类类型查询
        wrapper.eq(categoryPageQueryDTO.getType()!=null,
                Category::getType,categoryPageQueryDTO.getType());
        // 排序
        wrapper.orderByAsc(Category::getSort);
        Page<Category> page = this.page(categoryPage, wrapper);
        return new PageResult(page.getTotal(),page.getRecords());
    }

    private boolean judgeCategory(){
        return true;
    }
}
