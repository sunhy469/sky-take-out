package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

public interface ICategoryService extends IService<Category> {
    PageResult queryPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void updateCategory(CategoryDTO categoryDTO);

    void updateStatus(Integer status,Long id);

    void saveCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
