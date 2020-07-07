package com.jonathanlee.mapper;

import com.jonathanlee.domain.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

//    保存菜单
    void saveMenu(Menu menu);

//    查询该id对应的menu
    Long selectParentId(Long id);

//    更新菜单关系
    void updateMenuRel(Long id);

//    获取树形菜单数据
    List<Menu> getTreeData();
}