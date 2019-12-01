package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAllByPage(int page, int rows) {
        int start = (page - 1) * rows;
        List<Article> articles = articleDao.findAllByPage(start, rows);
        //总数据数
        int count = articleDao.totalCount();
        //总页数
        int totalPage = count % rows == 0 ? count / rows : count / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalPage);
        map.put("records", count);
        map.put("rows", articles);
        map.put("page", page);
        return map;
    }

    @Override
    public void insertArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setIssueTime(new Date());
        article.setUploadTime(new Date());
        articleDao.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findArticleById(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Article articleById = articleDao.findArticleById(id);
            map.put("id", articleById.getId());
            map.put("code", 200);
            map.put("link", "http://xxx/1000.html");
            map.put("ext", "");
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "参数错误");
            e.printStackTrace();
        }
        return map;
    }
}
