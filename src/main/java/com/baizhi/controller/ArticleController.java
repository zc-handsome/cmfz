package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("findAllByPage")
    public Map<String, Object> findAllByPage(int page, int rows) {
        Map<String, Object> map = articleService.findAllByPage(page, rows);
        return map;
    }

    @RequestMapping("addArticle")
    public void addArticle(Article article) {
        articleService.insertArticle(article);
    }

    @RequestMapping("updateArticle")
    public void updateArticle(Article article) {
        articleService.updateArticle(article);
    }

    @RequestMapping("/detail")
    public Map<String, Object> detail(String id, String uidc) {
        return articleService.findArticleById(id);
    }
}
