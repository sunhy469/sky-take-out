package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

public interface IDishService extends IService<Dish> {
    PageResult queryPage(DishPageQueryDTO dto);
}
