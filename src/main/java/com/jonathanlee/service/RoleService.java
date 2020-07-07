package com.jonathanlee.service;

import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;
import com.jonathanlee.domain.Role;

import java.util.List;

public interface RoleService {

    //  查询所有角色
    public PageListRes getRoles(QueryVo queryVo);

//    保存角色权限
    void saveRole(Role role);

//    更新角色的业务
    void updateRole(Role role);

//    删除角色的业务
    void deleteRole(Long rid);

//    获取所有角色列表
    List<Role> roleList();

//    根据员工id 查询对应的角色
    List<Long> getRoleByEid(Long id);
}