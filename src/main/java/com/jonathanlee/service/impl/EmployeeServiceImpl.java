package com.jonathanlee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jonathanlee.domain.Employee;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;
import com.jonathanlee.domain.Role;
import com.jonathanlee.mapper.EmployeeMapper;
import com.jonathanlee.service.EmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 事务
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 查询所有员工
    @Override
    public PageListRes getEmployee(QueryVo queryVo) {
        // 设置分页
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());

        // 查询员工
        List<Employee> employees = employeeMapper.selectAllEmployee(queryVo);

        // 封装成pageList
        PageListRes pageListRes = new PageListRes();

        // 设置总数
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    // 保存员工
    @Override
    public void saveEmployee(Employee employee) {

//        密码进行加密处理
//        第一个参数：加密的内容
//        第二个参数：需不需要加盐
//        第三个参数：散列
        if(employee.getPassword() != null){
            Md5Hash md5Hash = new Md5Hash(employee.getPassword(), employee.getUsername(), 2 );
            employee.setPassword(md5Hash.toString());
        }

        // 保存员工
        employeeMapper.insert(employee);

        // 保存角色 关系表
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(), role.getRid());
        }
    }

    // 更新员工
    @Override
    public void updateEmployee(Employee employee) {
        // 打破也角色之间的关系
        employeeMapper.deleteRoleRel(employee.getId());

        // 更新员工
        employeeMapper.updateByPrimaryKey(employee);

        // 重新建立与角色的关系
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(), role.getRid());
        }
    }

    // 设置员工离职状态
    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

//    根据用户到数据库当中查询有没有当前用户
    @Override
    public Employee getEmployeeWithUserName(String username) {
        return employeeMapper.getEmployeeWithUserName(username);
    }

//    根据用户id查询角色编号名称
    @Override
    public List<String> getRolesById(Long id) {
        return employeeMapper.getRolesById(id);
    }

//    根据用户的id查询权限的名称    资源名称
    @Override
    public List<String> getPermissionById(Long id) {
        return employeeMapper.getPermissionById(id);
    }
}