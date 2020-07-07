package com.jonathanlee.mapper;

import com.jonathanlee.domain.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> selectAll();
}