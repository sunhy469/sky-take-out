package com.sky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

//    自定义注解方式填充公共字段 ，这个方法没有使用，用的是mp注解
//    @AutoFill(value = OperationType.INSERT)
}
