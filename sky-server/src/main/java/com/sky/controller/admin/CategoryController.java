package com.sky.controller.admin;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类管理")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/page")
    @Operation(summary = "分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询");
        PageResult pageResult = categoryService.queryPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PutMapping
    @Operation(summary = "修改分类")
    public Result<T>  updateCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类");
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    // TODO 涉及多表修改
    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result<String> delete(Long id){
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "修改分类状态")
    public Result<T> updateStatus(@PathVariable Integer status,@NotNull Long id){
        log.info("修改分类状态");
        categoryService.updateStatus(status,id);
        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增分类")
    public Result<T> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类");
        categoryService.saveCategory(categoryDTO);
        return Result.success();
    }
}
