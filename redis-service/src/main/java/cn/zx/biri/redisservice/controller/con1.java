package cn.zx.biri.redisservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:34 2019/1/9 0009
 */
@Controller
public class con1 {
    @ResponseBody
    @GetMapping("get")
    public String get(String name){

        System.out.println("name="+name);

        return "name="+name;

    }
}
