package com.jonathanlee.service;

import com.jonathanlee.domain.Employee;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;

import java.util.List;

public interface EmployeeService {

    // 查询员工
    public PageListRes getEmployee(QueryVo queryVo);

    // 添加员工
    void saveEmployee(Employee employee);

    // 更新员工
    void updateEmployee(Employee employee);

    // 设置员工离职状态
    void updateState(Long id);

//    根据用户到数据库当中查询有没有当前用户
    Employee getEmployeeWithUserName(String username);

//    根据用户id查询角色编号名称
    List<String> getRolesById(Long id);

//    根据用户的id查询权限的名称    资源名称
    List<String> getPermissionById(Long id);
}