package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.IDishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Tag(name = "菜品管理")
public class DishController {
    private  final IDishService dishService;
    
    public DishController (IDishService dishService){
        this.dishService=dishService;
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品数据")
    public Result<PageResult> page(DishPageQueryDTO dto){
        log.info("分页查询菜品数据");
        PageResult pageResult = dishService.queryPage(dto);
        return Result.success(pageResult);
    }
}
