package com.jonathanlee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.Permission;
import com.jonathanlee.domain.QueryVo;
import com.jonathanlee.domain.Role;
import com.jonathanlee.mapper.RoleMapper;
import com.jonathanlee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    // 注入mapper
    @Autowired
    private RoleMapper roleMapper;

    // 查询所有角色
    @Override
    public PageListRes getRoles(QueryVo queryVo) {
        // 调用mapper查询数据
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Role> roles = roleMapper.selectAll();

        // 封装成pageList
        PageListRes pageListRes = new PageListRes();

        // 总记录数
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roles);

        return pageListRes;
    }

//    保存角色权限
    @Override
    public void saveRole(Role role) {
//        1.保存角色

        roleMapper.insert(role);
//        2.保存角色与权限之间的关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

//    更新角色的业务
    @Override
    public void updateRole(Role role) {
        // 打破角色与权限之前的关系
        roleMapper.deletePermissionRel(role.getRid());
        // 更新角色
        roleMapper.updateByPrimaryKey(role);
        // 重新建立与权限的关系
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

//    删除角色的业务
    @Override
    public void deleteRole(Long rid) {
        // 删除关联的权限
        roleMapper.deletePermissionRel(rid);

        // 删除对应的角色
        roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }

//    根据员工id 查询对应的角色
    @Override
    public List<Long> getRoleByEid(Long id) {
        return roleMapper.getRoleWithId(id);
    }

}
