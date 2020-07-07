package com.jonathanlee.service;

import com.jonathanlee.domain.Permission;

import java.util.List;

public interface PermissionService {

    // 获取所有权限
    public List<Permission> getPermissions();

    // 根据角色查询对应的权限
    List<Permission> getPermissionByRid(Long rid);
}