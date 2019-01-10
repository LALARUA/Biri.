package cn.zx.biri.loginregister.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:40 2019/1/9 0009
 */
@Controller
public class con1 {


    @GetMapping("/get")
    public String get(HttpSession httpSession,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        System.out.println("loginAndRegister SessionId="+httpSession.getId());
        httpSession.setAttribute("name","zx");
        return "sf";
    }
}
