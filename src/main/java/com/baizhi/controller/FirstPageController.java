package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FirstPageController {

    @Autowired
    private FirstPageService firstPageService;


    @RequestMapping("first_page")
    public Map<String, Object> firstPage(String uid, String type, String sub_type) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if (type.equals("all")) {
            try {
                List<Banner> fiveBanners = firstPageService.findFiveBanners();
                List<Album> sixAlbum = firstPageService.findSixAlbum();
                List<Article> twoArticle = firstPageService.findTwoArticle();
                map.put("code", 200);
                map.put("header", fiveBanners);
                map1.put("albums", sixAlbum);
                map1.put("articles", twoArticle);
                map.put("body", map1);
            } catch (Exception e) {
                map.put("code", 500);
                map.put("msg", "参数错误");
                e.printStackTrace();
            }
        } else if (type.equals("wen")) {
            try {
                List<Album> sixAlbum = firstPageService.findSixAlbum();
                map.put("code", 200);
                map1.put("albums", sixAlbum);
                map.put("body", map1);
            } catch (Exception e) {
                map.put("code", 500);
                map.put("msg", "参数错误");
                e.printStackTrace();
            }

        } else if (type.equals("si")) {
            try {
                List<Article> twoArticle = firstPageService.findTwoArticle();
                map.put("code", 200);
                map1.put("articles", twoArticle);
                map.put("body", map1);
            } catch (Exception e) {
                map.put("code", 500);
                map.put("msg", "参数错误");
                e.printStackTrace();
            }
        }
        return map;
    }

}
