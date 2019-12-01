package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAllAlbumByPage(int page, int rows) {
        int start = (page - 1) * rows;
        List<Album> albums = albumDao.findAllAlbumByPage(start, rows);
        //专辑的数量
        Integer count = albumDao.totalAlbum();
        //总页数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("rows", albums);
        map.put("records", count);
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    @Override
    public String insertAlbum(Album album) {
        String id = UUID.randomUUID().toString();
        album.setId(id).setStatus("1").setChapterNum(0);
        albumDao.insertAlbum(album);
        return id;
    }

    @Override
    public void updateAlbumById(Album album) {
        albumDao.updateAlbumById(album);
    }

    @Override
    public void deleteAlbumById(String[] id) {
        albumDao.deleteAlbumById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Album findAlbumById(String id) {
        Album albumById = albumDao.findAlbumById(id);
        return albumById;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> detail(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Album detail = albumDao.detail(id);
            List<Chapter> chapters = chapterDao.findChaptersByAlbumId(id);
            map.put("code", 200);
            map.put("introduction", detail);//专辑的详细信息
            map.put("list", chapters);
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "参数错误");
            e.printStackTrace();
        }
        return map;
    }
}
