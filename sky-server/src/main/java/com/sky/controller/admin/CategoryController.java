package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
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

    /**
     * 分类数据分页查询，主页这个用的mp,需要配置一个分页拦截器
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询");
        PageResult pageResult = categoryService.queryPage(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改分类信息，注意允许修改的条件
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改分类")
    public Result<T>  updateCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类");
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    /**
     *  删除分类，涉及关联表的查询修改，注意删除时满足的条件
     * @param id
     * @return
     */
    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result<String> delete(@NotNull Long id){
        log.info("删除分类"+id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "修改分类状态")
    public Result<T> updateStatus(@PathVariable Integer status,@NotNull Long id){
        log.info("修改分类状态");
        categoryService.updateStatus(status,id);
        return Result.success();
    }

    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增分类")
    public Result<T> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类");
        categoryService.saveCategory(categoryDTO);
        return Result.success();
    }
}
