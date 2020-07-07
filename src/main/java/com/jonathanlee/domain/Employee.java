package com.jonathanlee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Employee {
    private Long id;

    private String username;

    private String password;

    // 数据库返回时间戳 要转换
    // 传送给数据库也是日期格式
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private String tel;

    private String email;

    private Boolean state;

    private Boolean admin;
    private Long dep_id;

    private Department department;

    // 角色
    private List<Role> roles = new ArrayList<>();
}