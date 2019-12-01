package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
* private String id;
    private String title;
    private double size;
    private String duration;//时长
    private Date uploadDate;
    private String Mp3Name;
    private String albumId;*/
@Mapper
public interface ChapterDao {
    //查询指定专辑下的所有的章节，分页
    @Select("select id,title,size,duration,upload_date as uploadDate,mp3_name as mp3Name" +
            " from chapter where album_id=#{albumId} limit #{start},#{rows}")
    public List<Chapter> findAllChapterByAlbumId(@Param("albumId") String albumId, @Param("start") int start, @Param("rows") int rows);

    @Select("select count(id) from chapter where album_id=#{albumId}")
    public int totalChapter(String albumId);

    //添加新章节
    @Insert("insert into chapter(id,title,size,duration,upload_date,mp3_name,album_id)" +
            " values(#{id},#{title},#{size},#{duration},#{uploadDate},#{mp3Name},#{albumId})")
    public void insertChapter(Chapter chapter);

    //修改章节
    public void updateChapter(Chapter chapter);

    //批量删除章节
    public void deleteChapterById(String[] id);

    //根据章节id查询章节
    @Select("select id,title,size,duration,upload_date as uploadDate,mp3_name as mp3Name" +
            " from chapter where id=#{id}")
    public Chapter findChapterById(String id);

    //根据传递的多个专辑id批量删除其下所有的章节
    public void deleteChaptersByAlbumId(String[] id);

    //查询指定专辑下的所有的章节，不分页
    @Select("select id,title,size,duration,upload_date as uploadDate,mp3_name as mp3Name" +
            " from chapter where album_id=#{albumId}")
    public List<Chapter> findChaptersByAlbumId(String albumId);
}
