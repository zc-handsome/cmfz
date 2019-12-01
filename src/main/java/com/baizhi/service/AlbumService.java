package com.baizhi.service;


import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //查询所有专辑并分页
    public Map<String, Object> findAllAlbumByPage(int page, int rows);

    public String insertAlbum(Album album);

    //修改新的专辑
    public void updateAlbumById(Album album);

    //删除新的专辑
    public void deleteAlbumById(String[] id);

    public Album findAlbumById(String id);

    //查询专辑详情
    public Map<String, Object> detail(String id);
}
