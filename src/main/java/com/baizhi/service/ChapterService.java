package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    public Map<String, Object> findAllChapterByAlbumId(String albumId, int page, int rows);

    public void deleteChapterById(String[] id, String parentId);

    //添加
    public String insertChapter(Chapter chapter, String parentId);

    //修改
    public void updateChapter(Chapter chapter);

    public Chapter findChapterById(String id);

    //根据传递的多个专辑id批量删除其下所有的章节
    public void deleteChaptersByAlbumId(String[] id);
}
