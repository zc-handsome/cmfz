package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;

import java.util.List;

public interface FirstPageService {
    //五张最新轮播图
    public List<Banner> findFiveBanners();

    //所有的专辑
    public List<Album> findSixAlbum();

    //展示用户上师的文章
    public List<Article> findTwoArticle();
}
