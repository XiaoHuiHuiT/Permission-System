package com.jonathanlee.mapper;

import com.jonathanlee.domain.Employee;
import com.jonathanlee.domain.QueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    // 查询所有员工
    public List<Employee> selectAllEmployee(QueryVo queryVo);

    // 添加员工
    void insert(Employee employee);

    // 更新员工
    void updateByPrimaryKey(Employee employee);

    // 设置员工离职状态
    void updateState(Long id);

//    保存角色 关系表
    void insertEmployeeAndRoleRel(@Param("id") Long id, @Param("rid") Long rid);

//    打破也角色之间的关系
    void deleteRoleRel(Long id);

//    根据用户到数据库当中查询有没有当前用户
    Employee getEmployeeWithUserName(String username);

//    根据用户id查询角色编号名称
    List<String> getRolesById(Long id);

//    根据用户的id查询权限的名称    资源名称
    List<String> getPermissionById(Long id);
}