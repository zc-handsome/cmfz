package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    //查询所有文章并分页
    @Select("select id,title,status,issue_time as issueTime,upload_time as uploadTime,author,content" +
            " from article limit #{start},#{rows}")
    public List<Article> findAllByPage(@Param("start") int start, @Param("rows") int rows);

    //查询数据总条数
    @Select("select count(id) from article")
    public Integer totalCount();

    //插入数据；富文本编辑器存储内容，文字是文字，其它的是将其转化为标签存储
    @Insert("insert into article(id,title,status,issue_time,upload_time,author," +
            "content) values(#{id},#{title},#{status},#{issueTime},#{uploadTime},#{author},#{content})")
    public void insertArticle(Article article);

    //修改文章数据
    @Update("update article set title=#{title},author=#{author},content=#{content} where id=#{id}")
    public void updateArticle(Article article);

    //点击查询文章详情
    @Select("select id,title,status,issue_time as issueTime,upload_time as uploadTime,author,content from article" +
            " where id=#{id}")
    public Article findArticleById(String id);
}
