package cn.zx.biri.zuul.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:52 2019/1/10 0010
 */
@Controller
public class AuthenticateController {





    @ResponseBody
    @GetMapping("test")
    public String test(){
        return "success";

    }
    @ResponseBody
    @PostMapping("authenticate")
    public String authenticate(String username, String password, HttpServletRequest httpServletRequest){
        SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
        if (Objects.isNull(savedRequest))
            return "";
        String requestUrl = savedRequest.getRequestUrl();
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println(currentUser.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        currentUser.login(token);
        System.out.println(currentUser.isAuthenticated());
        return "success";
    }
}
