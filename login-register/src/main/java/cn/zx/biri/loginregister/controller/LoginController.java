package cn.zx.biri.loginregister.controller;

import cn.zx.biri.loginregister.feignService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:52 2019/1/10 0010
 */
@Controller
public class LoginController {
    @Autowired
    RedisService redisService;

    @ResponseBody
    @GetMapping("loginPage")
    public String loginPage(){
        return "hello world";
    }



    @ResponseBody
    @GetMapping("authenticate")
    public String authenticate(String username, String password, HttpServletRequest httpServletRequest){

        try {
            redisService.get("zhongxiang");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ss";
    }
}
