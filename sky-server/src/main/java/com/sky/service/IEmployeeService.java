package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface IEmployeeService extends IService<Employee> {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    // 新增员工信息
    void save(EmployeeDTO employeeDTO);

    // 分页查询员工信息
    PageResult pageQuery(EmployeePageQueryDTO queryDTO);

    // 更新员工状态信息
    void updateStatus(Integer status, Long id);
}
