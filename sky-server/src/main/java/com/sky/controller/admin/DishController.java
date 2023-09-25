package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.IDishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Tag(name = "菜品管理")
public class DishController {
    private final IDishService dishService;

    public DishController(IDishService dishService) {
        this.dishService = dishService;
    }

    @DeleteMapping
    @Operation(summary = "删除菜品")
    public Result<String>delete(Long[] ids){
        log.info("删除菜品");
        dishService.deleteDish(ids);
        return Result.success("删除成功");
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询菜品数据")
    public Result<PageResult> page(DishPageQueryDTO dto) {
        log.info("分页查询菜品数据");
        PageResult pageResult = dishService.queryPage(dto);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "菜品状态修改")
    public Result<String> updateStatus(@PathVariable int status, Long id) {
        log.info("菜品状态修改");
        dishService.updateStatus(status, id);
        return Result.success("修改成功");
    }
}
