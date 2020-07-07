package com.jonathanlee.service.impl;

import com.jonathanlee.domain.Permission;
import com.jonathanlee.mapper.PermissionMapper;
import com.jonathanlee.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    // 注入mapper
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.selectAll();
    }

//    根据角色查询对应的权限
    @Override
    public List<Permission> getPermissionByRid(Long rid) {
        return permissionMapper.selectPermissionByRid(rid);
    }
}
