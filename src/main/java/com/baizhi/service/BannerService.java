package com.baizhi.service;


import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    //查询所有的轮播图并分页展示
    public List<Banner> findAllByPage(int page, int rows);

    public int selectTotalCount();

    public void insertBanner(Banner banner);

    public void deleteBannerById(String[] ids);

    public void updateBannerById(Banner banner);

    public Banner findBannerById(String id);

    public List<Banner> findAll();
}
