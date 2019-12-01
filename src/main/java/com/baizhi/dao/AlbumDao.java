package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumDao {
    /*
    *  private String id;
        private String author;
        private String announcer;
        private Integer chapterNum;
        private Integer score;
        private String albumIntro;
        private String status;
        private Date issueTime;//发行时间
        private Date uploadTime;*/
    //查询所有的专辑以及其下的所有章节
    @Select("select id,title,author,announcer,chapter_num as chapterNum,score,album_intro as albumIntro," +
            "status, issue_time as issueTime,upload_time as uploadTime" +
            ",picture from album limit #{start},#{rows}")
    public List<Album> findAllAlbumByPage(@Param("start") int start, @Param("rows") int rows);

    //查询专辑数量
    @Select("select count(id) from album")
    public Integer totalAlbum();

    //插入新的专辑
    @Insert("insert into album(id,title,author,announcer," +
            "chapter_num,score,album_intro,status,issue_time,upload_time,picture) values(#{id},#{title},#{author},#{announcer},#{chapterNum}," +
            "#{score},#{albumIntro},#{status},#{issueTime},#{uploadTime},#{picture})")
    public void insertAlbum(Album album);

    //修改新的专辑
    public void updateAlbumById(Album album);

    //删除新的专辑
    public void deleteAlbumById(String[] id);

    //查询专辑
    @Select("select id,title,author,announcer,chapter_num as chapterNum,score,album_intro as albumIntro," +
            "status, issue_time as issueTime,upload_time as uploadTime" +
            ",picture from album where id=#{id}")
    public Album findAlbumById(String id);

    //修改章节数
    @Update("update album set chapter_num=#{chapterNum} where id=#{id}")
    public void updateChapterNum(@Param("id") String id, @Param("chapterNum") int chapterNum);

    //查询指定专辑的详情
    public Album detail(String id);
}
