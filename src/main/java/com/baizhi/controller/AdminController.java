package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request, String code) {
        Admin admin = adminService.AdminLogin(name, pwd);
        HttpSession session = request.getSession();
        Object validationCode = session.getAttribute("validationCode");
        if (validationCode.equals(code)) {
            if (admin != null) {
                session.setAttribute("admin", admin);
                return "success";
            } else {
                return "用户名或密码输入错误";
            }
        }
        return "验证码输入错误";
    }

    //退出登录
    @RequestMapping("/logout")
    public String saveLogOut(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login.jsp";
    }

}
