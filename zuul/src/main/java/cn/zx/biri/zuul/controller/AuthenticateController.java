package cn.zx.biri.zuul.controller;


import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.zuul.config.service.AuthenticateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:52 2019/1/10 0010
 */
@Controller
public class AuthenticateController {
    @Autowired
    AuthenticateService authenticateService;

    @ResponseBody
    @GetMapping("test")
    public String test(){
        return "success";

    }

    @PostMapping("loginPage")
    public String loginPage(HttpServletRequest httpServletRequest){
        if (1==1)
        return "login";

        Subject currentUser = SecurityUtils.getSubject();

        //如果当前用户已经认证，返回首页
//        if (currentUser.isAuthenticated())
//            return "index";

        Cookie userInCookie = CookieUtils.getCookieByName(httpServletRequest,"user");
        if (!Objects.isNull(userInCookie)){
            String userInfo = userInCookie.getValue();
            String[] split = userInfo.split("#");
            String username = split[0];
            String password = split[1];
            try {
                authenticateService.authenticateService(username,password,0);
            } catch (Exception e) {
                e.printStackTrace();
                return "login";

            }
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            if (Objects.isNull(savedRequest))
            return "index";
            String requestUrl = savedRequest.getRequestUrl();
            return requestUrl;
        }

        return "login";
    }



    @PostMapping("authenticate")
    public String authenticate(String username, String password, @RequestParam(required = false,defaultValue = "0") Integer rem, HttpServletRequest httpServletRequest){
//        SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
//        if (Objects.isNull(savedRequest))
//            return "";
//        String requestUrl = savedRequest.getRequestUrl();
        try {
            authenticateService.authenticateService(username,password,rem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "login";
    }
}
