package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerDao {

    //分页显示所有轮播图数据
    @Select("select id,title,status,create_time as createTime,url,descript from banner limit #{start},#{rows}")
    public List<Banner> findAllByPage(@Param("start") int start, @Param("rows") int rows);

    //查询所有条数
    @Select("select count(id) from banner")
    public int selectTotalCount();

    //添加轮播图
    @Insert("insert into banner(id,title,status,create_time,url,descript) values(#{id},#{title},#{status},#{createTime},#{url},#{descript})")
    public void insertBanner(Banner banner);

    //批量删除轮播图
    public void deleteBannerById(String[] ids);
    //修改轮播图

    public void updateBannerById(Banner banner);

    @Select("select id,title,status,create_time as createTime,url,descript from banner where id=#{id}")
    public Banner findBannerById(String id);

    //查询所有的轮播图信息
    @Select("select id,title,status,create_time as createTime,url,descript from banner")
    public List<Banner> findAll();
}
