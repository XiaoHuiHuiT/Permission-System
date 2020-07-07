package com.jonathanlee.web;

/*
处理所有权限请求的处理器
*/

import com.jonathanlee.domain.AjaxRes;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;
import com.jonathanlee.domain.Role;
import com.jonathanlee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

    // 注入业务层
    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String role(){
        return "role";
    }

    // 接收请求角色的列表 查询所有角色
    @RequestMapping("/getRoles")
    @ResponseBody
    public PageListRes getRoles(QueryVo queryVo){
        PageListRes roles = roleService.getRoles(queryVo);
        return roles;
    }

    // 接收保存角色地址
    @RequestMapping("/saveRole")
    @ResponseBody
    public AjaxRes saveRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用业务层，保存角色权限
            roleService.saveRole(role);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 更新角色请求
    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxRes updateRole(Role role){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用更新角色的业务
            roleService.updateRole(role);
            ajaxRes.setMsg("更新角色成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("更新角色失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 删除角色
    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxRes deleteRole(Long rid){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            // 调用删除角色的业务
            roleService.deleteRole(rid);
            ajaxRes.setMsg("删除角色成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("删除角色失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    // 获取所有角色列表
    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList(){
        return roleService.roleList();
    }

    // 根据员工id 查询对应的角色
    @RequestMapping("/getRoleByEid")
    @ResponseBody
    public List<Long> getRoleByEid(Long id){
        // 查询对应的角色
        return roleService.getRoleByEid(id);
    }
}