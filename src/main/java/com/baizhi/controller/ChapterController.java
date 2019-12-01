package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/findAllByPage")
    public Map<String, Object> findAllByPage(String albumId, int page, int rows) {
        Map<String, Object> allChapterByAlbumId = chapterService.findAllChapterByAlbumId(albumId, page, rows);
        return allChapterByAlbumId;
    }

    @RequestMapping("/edit")
    public String editChapter(String oper, Chapter chapter, String[] id, String parentId) {
        if (oper.equals("add")) {
            String s = chapterService.insertChapter(chapter, parentId);
            return s;
        } else if (oper.equals("edit")) {
            chapterService.updateChapter(chapter);
            return null;
        } else {
            chapterService.deleteChapterById(id, parentId);
            return null;
        }
    }

    @RequestMapping("/updateMp3Name")
    public void updateMp3Name(MultipartFile mp3Name, String cid, HttpSession session) throws EncoderException {
        String realPath = session.getServletContext().getRealPath("/chapter/");
        File file = new File(realPath);
        if (file.exists()) {
            file.mkdirs();
        }
        String originalFilename = mp3Name.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;
        File file1 = null;
        try {
            file1 = new File(file, newFileName);//要复制的文件对象
            mp3Name.transferTo(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Chapter chapter = chapterService.findChapterById(cid);
        chapter.setMp3Name(newFileName);
        chapter.setSize((double) mp3Name.getSize() / (1024 * 1024));//大小
        Encoder encoder = new Encoder();

        MultimediaInfo info = encoder.getInfo(file1);
        long ls = info.getDuration() / 1000 - 4;
        long minute = ls / 60;
        long second = ls % 60;
        chapter.setDuration(minute + "分" + second + "秒");
        chapter.setUploadDate(new Date());
        System.out.println(chapter);
        chapterService.updateChapter(chapter);
    }
}
