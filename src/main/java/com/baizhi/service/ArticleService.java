package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    public Map<String, Object> findAllByPage(int page, int rows);

    public void insertArticle(Article article);

    public void updateArticle(Article article);

    public Map<String, Object> findArticleById(String id);
}
