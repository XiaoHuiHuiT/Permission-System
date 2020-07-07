package com.jonathanlee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jonathanlee.domain.*;
import com.jonathanlee.mapper.MenuMapper;
import com.jonathanlee.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    //    注入mapper
    @Autowired
    private MenuMapper menuMapper;

    //    查询所有菜单
    @Override
    public PageListRes getMenuList(QueryVo vo) {
        // 设置分页
        Page<Object> page = PageHelper.startPage(vo.getPage(), vo.getRows());

        // 查询菜单
        List<Menu> menus = menuMapper.selectAll();

        // 封装成pageList
        PageListRes pageListRes = new PageListRes();

        // 设置总数
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(menus);
        return pageListRes;
    }

//    加载父菜单
    @Override
    public List<Menu> parentList() {
        return menuMapper.selectAll();
    }

//    保存菜单
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.saveMenu(menu);
    }

    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();

        /*
        判断  选择的父菜单  是不是自己的子菜单
        */
//        先取出当前选择的父菜单
        Long id = menu.getParent().getId();

        // 查询该id对应的menu
        Long parent_id = menuMapper.selectParentId(id);

        if(menu.getId() == parent_id){
            ajaxRes.setMsg("不能设置自己的子菜单为父菜单");
            ajaxRes.setSuccess(false);
            return ajaxRes;
        }

        try {
            // 调用业务层 更新菜单
            menuMapper.updateByPrimaryKey(menu);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

//    删除菜单
    @Override
    public AjaxRes deleteMenu(Long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
//            1.打破更新菜单关系
            menuMapper.updateMenuRel(id);

            // 2.调用业务层 删除菜单
            menuMapper.deleteByPrimaryKey(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

//    获取树形菜单数据
    @Override
    public List<Menu> getTreeData() {
        List<Menu> treeData = menuMapper.getTreeData();

        /*
        判断当前用户有没有对应的权限
        如果没有就从集合当中移除
        */

        // 获取用户 判断用户是否是管理员  是管理员就不需要做判断
        Subject subject = SecurityUtils.getSubject();
//        当前的用户     凭证信息
        Employee employee = (Employee)subject.getPrincipal();
        if(!employee.getAdmin()){
//            做权限校验
            checkPermission(treeData);
        }

        return treeData;
    }

    public void checkPermission(List<Menu> menus){
        // 获取主体
        Subject subject = SecurityUtils.getSubject();

//        遍历所有菜单及子菜单
        Iterator<Menu> iterator = menus.iterator();
        while(iterator.hasNext()){
            Menu menu = iterator.next();
            if(menu.getPermission() != null){
//                判断当前menu是否是权限对象，如果没有，当前遍历的菜单从集合当中移除
                String presource = menu.getPermission().getPresource();
                if(!subject.isPermitted(presource)){
                    // 当前遍历的菜单从集合当中移除
                    iterator.remove();
                    continue;
                }
            }

//            判断是否有子菜单，有子菜单也要做权限校验
            if(menu.getChildren().size() > 0){
                // 递归必须要有一个跳出的条件
                checkPermission(menu.getChildren());
            }
        }

    }
}