package com.jonathanlee.web.realm;

import com.jonathanlee.domain.Employee;
import com.jonathanlee.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取身份信息
        String username = (String)token.getPrincipal();

        // 到数据库当中查询没有有当前用户
        Employee employee = employeeService.getEmployeeWithUserName(username);
        System.out.println(employee);
        if(employee == null){
            return null;
        }
        /*
        认证
        参数：主题，正确的密码，盐，当前realm名称
        */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee,
                employee.getPassword(),
                ByteSource.Util.bytes(employee.getUsername()),
                this.getName());
        return info;
    }

    /*
    授权   在认证之后做的
    1.发现访问路径对应的方法上面 有授权注解   就会调用doGetAuthorizationInfo
    2.页面当中有授权标签 也会调用doGetAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权调用");

        // 获取用户的身份信息
        Employee employee = (Employee) principals.getPrimaryPrincipal();

        // 根据当前用户，查询角色和权限
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        // 判断当前用户是不是管理员，如果是管理员，拥有所有的权限
        if(employee.getAdmin()){
//            拥有所有的权限
            permissions.add("*:*");
        }else{
            // 查询角色
            roles = employeeService.getRolesById(employee.getId());
            // 查询权限
            permissions = employeeService.getPermissionById(employee.getId());
        }

        // 给授权信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }

}