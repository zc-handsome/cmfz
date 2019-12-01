package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map findUserByDate() {
        Integer county1 = userDao.findUserByDate(7, "y");
        Integer county2 = userDao.findUserByDate(14, "y");
        Integer county3 = userDao.findUserByDate(21, "y");
        List<Integer> listy = new ArrayList<>();
        listy.add(county1);
        listy.add(county2);
        listy.add(county3);
        List<Integer> listn = new ArrayList<>();
        Integer countn1 = userDao.findUserByDate(7, "n");
        Integer countn2 = userDao.findUserByDate(14, "n");
        Integer countn3 = userDao.findUserByDate(21, "n");
        listn.add(countn1);
        listn.add(countn2);
        listn.add(countn3);
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("data1", listy);
        map.put("data2", listn);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, List<Province>> groupByProvince() {
        List<Province> ylist = userDao.groupByProvince("y");
        List<Province> nlist = userDao.groupByProvince("n");
        /*List<Map<String,Object>> listy = new ArrayList<>();
        List<Map<String,Object>> listn = new ArrayList<>();
        for (Province province : ylist) {
            Map<String,Object> mapy = new HashMap<>();
            System.out.println(province);
            mapy.put("name",province.getName());
            mapy.put("value",province.getValue());
            listy.add(mapy);
        }
        for (Province province : nlist) {
            Map<String,Object> mapn = new HashMap<>();
            System.out.println(province);
            mapn.put("name",province.getName());
            mapn.put("value",province.getValue());
            listn.add(mapn);
        }*/

        /*Map<String, List<Map<String,Object>>> map = new HashMap<>();
        map.put("y",listy);
        map.put("n",listn);
        return map;*/
        Map<String, List<Province>> map = new HashMap<>();
        map.put("y", ylist);
        map.put("n", nlist);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Object findAllUser() {
        try {
            List<User> users = userDao.findAllUser();
            return users;
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", "-200");
            map.put("errmsg", "会员列表为空");
            return map;
        }
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }
}
