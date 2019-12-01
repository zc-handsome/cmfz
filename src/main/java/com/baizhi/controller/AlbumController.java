package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/findAllByPage")
    public Map<String, Object> findAllByPage(int page, int rows) {
        Map<String, Object> allAlbumByPage = albumService.findAllAlbumByPage(page, rows);
        return allAlbumByPage;
    }

    @RequestMapping("edit")
    public String editAlbum(String oper, Album album, String[] id) {
        if (oper.equals("add")) {
            String s = albumService.insertAlbum(album);
            return s;
        } else if (oper.equals("edit")) {
            albumService.updateAlbumById(album);
            return null;
        } else {
            chapterService.deleteChaptersByAlbumId(id);
            albumService.deleteAlbumById(id);
            return null;
        }
    }

    @RequestMapping("updateImgUrl")
    public void updateImgUrl(MultipartFile picture, String aid, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = picture.getOriginalFilename();
        String realName = new Date().getTime() + "_" + originalFilename;
        try {
            picture.transferTo(new File(file, realName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Album album = albumService.findAlbumById(aid);
        album.setPicture(realName);
        albumService.updateAlbumById(album);
    }

    @RequestMapping("/detail")
    public Map detail(String id, String uid) {
        Map<String, Object> detail = albumService.detail(id);
        return detail;
    }
}
