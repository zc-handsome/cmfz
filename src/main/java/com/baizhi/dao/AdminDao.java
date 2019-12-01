package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    //登录
    @Select("select id,name,pwd from admin where name=#{name} and pwd=#{pwd}")
    public Admin findAdminByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);
}
