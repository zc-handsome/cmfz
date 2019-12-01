package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("KindEditor")
public class KindEditorController {

    @RequestMapping("uploadImg")
    //上传图片后回显，以下面的方式回显数据
    //{"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
    public Map<String, Object> uploadImg(MultipartFile img, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img.getOriginalFilename();
        String newName = new Date().getTime() + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //封装数据将图片回显到编辑器中{"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
        //url为服务器的url
        Map<String, Object> map = new HashMap<>();
        map.put("error", 0);//"0"表示图片正确显示
        String scheme = request.getScheme();//获取协议的名称
        InetAddress localHost = Inet4Address.getLocalHost();//获取本地IP
        String address = localHost.getHostAddress();
        int serverPort = request.getServerPort();//获取端口号
        String contextPath = request.getContextPath();//获取项目名,/cmfz
        //拼接url
        String url = scheme + "://" + address + ":" + serverPort + contextPath + "/img/" + newName;

        map.put("url", url);
        return map;
    }

    //显示图库中上传的所有图片

    @RequestMapping("getAllImg")
    public Map<String, Object> getAllImg(HttpServletRequest request) throws UnknownHostException {
        /*
        * {
	"moveup_dir_path": "",
	"current_dir_path": "",
	"current_url": "\/ke4\/php\/..\/attached\/",
	"total_count": 5,
	"file_list": [
	{
		"is_dir": false,
		"has_file": false,
		"filesize": 208736,
		"dir_path": "",
		"is_photo": true,
		"filetype": "jpg",
		"filename": "1241601537255682809.jpg",
		"datetime": "2018-06-06 00:36:39"
	},
	    ...
	]
	*/
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        String realPath = request.getSession().getServletContext().getRealPath("/img/");
        File file = new File(realPath);
        String[] names = file.list();
        for (String name : names) {
            Map<String, Object> item = new HashMap<>();
            item.put("is_dir", false);
            item.put("has_file", file);
            File file1 = new File(realPath, name);
            item.put("filesize", file1.length());
            item.put("dir_path", "");
            item.put("is_photo", true);
            int i = name.lastIndexOf(".");
            //String str = name.substring(i);
            // System.out.println(str);
            String s = name.substring(i + 1);
            item.put("filetype", s);
            item.put("filename", name);
            boolean contains = name.contains("_");
            if (contains) {
                String s1 = name.split("_")[0];
                Long aLong = Long.valueOf(s1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(aLong);
                item.put("datetime", format);
            }
            if (!contains) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                item.put("datetime", sdf.format(new Date()));
            }
            list.add(item);
        }
        String scheme = request.getScheme();
        InetAddress localHost = Inet4Address.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String url = scheme + "://" + hostAddress + ":" + serverPort + contextPath + "/img/";

        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        map.put("current_url", url);
        map.put("total_count", names.length);
        map.put("file_list", list);
        return map;
    }
}
