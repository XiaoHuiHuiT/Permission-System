package com.jonathanlee.service;

import com.jonathanlee.domain.AjaxRes;
import com.jonathanlee.domain.Menu;
import com.jonathanlee.domain.PageListRes;
import com.jonathanlee.domain.QueryVo;

import java.util.List;

public interface MenuService {

    // 查询所有菜单
    PageListRes getMenuList(QueryVo vo);

//    加载父菜单
    List<Menu> parentList();

//    保存菜单
    void saveMenu(Menu menu);

//    更新菜单
    AjaxRes updateMenu(Menu menu);

//    删除菜单
    AjaxRes deleteMenu(Long id);

//    获取树形菜单数据
    List<Menu> getTreeData();
}