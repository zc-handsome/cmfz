package com.baizhi.service;

import com.baizhi.dao.FirstPageDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirstPageServiceImpl implements FirstPageService {
    @Autowired
    private FirstPageDao firstPageDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findFiveBanners() {
        return firstPageDao.findFiveBanners();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findSixAlbum() {
        return firstPageDao.findSixAlbum();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findTwoArticle() {
        return firstPageDao.findTwoArticle();
    }
}
