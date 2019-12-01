package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/findAllByPage")
    public Map<String, Object> findAllByPage(int page, int rows) {
        List<Banner> allByPage = bannerService.findAllByPage(page, rows);
        //总数据
        int count = bannerService.selectTotalCount();
        //总页数
        int total = count % rows == 0 ? count / rows : count / rows + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("rows", allByPage);//当前页数据
        map.put("page", page);//第几页
        map.put("records", count);//总数据条数
        map.put("total", total);//总页数
        return map;
    }

    @RequestMapping("/edit")
    public String editBanner(String oper, Banner banner, String[] id) {
        if (oper.equals("add")) {
            banner.setId(UUID.randomUUID().toString());
            banner.setCreateTime(new Date());
            bannerService.insertBanner(banner);
            return banner.getId();
        } else if (oper.equals("edit")) {
            bannerService.updateBannerById(banner);
            return null;
        } else {
            bannerService.deleteBannerById(id);
            return null;
        }
    }

    @RequestMapping("updateUrl")
    public void updateUrl(MultipartFile url, String bId, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = url.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + filename;
        try {
            url.transferTo(new File(file, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //修改数据库的路径
        Banner banner = bannerService.findBannerById(bId);
        banner.setUrl(newFileName);
        bannerService.updateBannerById(banner);
    }

    //导出数据到xls文件
    @RequestMapping("exportFile")
    public void exportFile(String xlsName, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(xlsName);
        //查询所有的轮播图数据
        List<Banner> banners = bannerService.findAll();
        String realPath = request.getSession().getServletContext().getRealPath("/xls");
        String realPath1 = request.getSession().getServletContext().getRealPath("/img");
        for (Banner banner : banners) {
            banner.setUrl(realPath1 + "/" + banner.getUrl());
        }
        System.out.println(realPath);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图数据表", "轮播图"), Banner.class, banners);
        ServletOutputStream outputStream = null;
        try {
            workbook.write(new FileOutputStream(new File(realPath + "/" + xlsName)));
            File file = new File(realPath, xlsName);
            if (!file.exists()) {
                file.mkdirs();
            }
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(xlsName, "UTF-8"));
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
