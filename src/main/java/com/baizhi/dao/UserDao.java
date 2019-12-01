package com.baizhi.dao;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao {
    //近几周用户注册量
    public Integer findUserByDate(@Param("time") Integer time, @Param("sex") String sex);

    //用户地区分布
    public List<Province> groupByProvince(String sex);

    //添加用户
    public void insertUser(User user);

    //查询所有的用户
    public List<User> findAllUser();
}
