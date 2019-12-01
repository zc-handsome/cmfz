package com.baizhi.service;


import com.baizhi.entity.Province;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map findUserByDate();

    // public  Map<String,List<Map<String,Object>>> groupByProvince();
    public void insertUser(User user);

    public Map<String, List<Province>> groupByProvince();

    public Object findAllUser();
}
