package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAllChapterByAlbumId(String albumId, int page, int rows) {
        int start = (page - 1) * rows;
        List<Chapter> chapters = chapterDao.findAllChapterByAlbumId(albumId, start, rows);
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
        //专辑的数量
        Integer count = chapterDao.totalChapter(albumId);
        System.out.println(count);
        //总页数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("rows", chapters);
        map.put("records", count);
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    @Override
    public void deleteChapterById(String[] id, String parentId) {
        chapterDao.deleteChapterById(id);
        Album albumById = albumDao.findAlbumById(parentId);
        albumDao.updateChapterNum(parentId, albumById.getChapterNum() - id.length);
    }

    @Override
    public String insertChapter(Chapter chapter, String parentId) {
        String id = UUID.randomUUID().toString();
        chapter.setId(id).setAlbumId(parentId).setUploadDate(new Date());
        chapterDao.insertChapter(chapter);
        Album albumById = albumDao.findAlbumById(parentId);
        albumDao.updateChapterNum(parentId, albumById.getChapterNum() + 1);
        return id;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapterDao.updateChapter(chapter);
    }

    @Override
    public void deleteChaptersByAlbumId(String[] id) {
        chapterDao.deleteChaptersByAlbumId(id);

    }

    @Override
    public Chapter findChapterById(String id) {
        return chapterDao.findChapterById(id);
    }
}
