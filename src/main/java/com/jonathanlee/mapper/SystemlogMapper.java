package com.jonathanlee.mapper;

import com.jonathanlee.domain.Systemlog;

import java.util.List;

public interface SystemlogMapper {
    int deleteByPrimaryKey(Long id);

    void insert(Systemlog record);

    Systemlog selectByPrimaryKey(Long id);

    List<Systemlog> selectAll();

    int updateByPrimaryKey(Systemlog record);
}