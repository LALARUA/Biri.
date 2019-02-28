package cn.zx.biri.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:33 2019/2/27 0027
 */
@Controller
public class tt {
    @GetMapping("tt")
    public String tt(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("uu", "hh");
        cookie.setMaxAge(60);
        cookie.setPath("/");
//        cookie.setDomain("localhost");
        httpServletResponse.addCookie(cookie);
        return "tt";
    }
}
