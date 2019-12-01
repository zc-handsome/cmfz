package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findAllByPage(int page, int rows) {
        int start = (page - 1) * rows;
        return bannerDao.findAllByPage(start, rows);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int selectTotalCount() {
        return bannerDao.selectTotalCount();
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerDao.insertBanner(banner);
    }

    @Override
    public void deleteBannerById(String[] ids) {
        bannerDao.deleteBannerById(ids);
    }

    @Override
    public void updateBannerById(Banner banner) {
        bannerDao.updateBannerById(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Banner findBannerById(String id) {
        return bannerDao.findBannerById(id);
    }

    @Override
    public List<Banner> findAll() {
        return bannerDao.findAll();
    }
}
