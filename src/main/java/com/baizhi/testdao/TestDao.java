package com.baizhi.testdao;

import com.baizhi.CmfzSpringBoot;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = CmfzSpringBoot.class)
@RunWith(value = SpringRunner.class)
public class TestDao {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Test
    public void testAdmin() {
        Admin zc = adminDao.findAdminByNameAndPwd("zc", "123456");
        System.out.println(zc);
    }

    @Test
    public void testFindAllByPage() {
        List<Banner> allByPage = bannerDao.findAllByPage(2, 2);
        for (Banner banner : allByPage) {
            System.out.println(banner);
        }
    }

    @Test
    public void testFindAlbumByPage() {
        List<Album> allAlbumByPage = albumDao.findAllAlbumByPage(0, 2);
        for (Album album : allAlbumByPage) {
            System.out.println(album);
        }
    }

    @Test
    public void testChapter() {
        List<Chapter> allChapterByAlbumId = chapterDao.findAllChapterByAlbumId("1001", 0, 2);
        for (Chapter chapter : allChapterByAlbumId) {
            System.out.println(chapter);
        }

    }
}
