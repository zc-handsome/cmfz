package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    //登录
    public Admin AdminLogin(String name, String pwd);
}
