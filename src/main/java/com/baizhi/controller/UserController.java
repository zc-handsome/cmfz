package com.baizhi.controller;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.ShortMessageVerificationCode;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findCount")
    public Map findUserCountByDate() {
        Map userByDate = userService.findUserByDate();
        return userByDate;
    }

    @RequestMapping("/groupByProvince")
    public Map groupByProvince() {
        // Map<String, List<Map<String, Object>>> map = userService.groupByProvince();
        Map<String, List<Province>> map = userService.groupByProvince();
        return map;
    }

    @RequestMapping("/addUser")
    public void addUser(User user) {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-d0f674c74da94dacb0be49d387214a9e");
        try {
            user.setId(UUID.randomUUID().toString());
            user.setCreateTime(new Date());
            userService.insertUser(user);
            goEasy.publish("my_channel", "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            goEasy.publish("my_channel", "添加失败!");
        }
    }

    @RequestMapping("/findAll")
    public Object findAll() {
        Object allUser = userService.findAllUser();
        return allUser;
    }

    @RequestMapping("/getSMS")
    public Map<String, String> getSMS(String phone, String code) throws IOException {
        String smsCode = ShortMessageVerificationCode.SMS(phone);
        Map<String, String> map = new HashMap<>();
        if (code.equals(smsCode)) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }
}
