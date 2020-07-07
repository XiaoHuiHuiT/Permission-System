package com.jonathanlee.service.impl;

import com.jonathanlee.domain.Department;
import com.jonathanlee.mapper.DepartmentMapper;
import com.jonathanlee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    // 注入mapper
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentList() {
        return departmentMapper.selectAll();
    }
}
