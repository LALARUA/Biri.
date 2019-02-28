package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.webservice.feignService.LoginAndRegisterService;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:06 2019/2/27 0027
 */

@Controller
public class loginAndRegisterController {

    @Autowired
    LoginAndRegisterService loginAndRegisterService;
    @GetMapping("login")
    public String login(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getSession().getId();
        Cookie userInCookie = CookieUtils.getCookieByName(httpServletRequest,"user");
        if (!Objects.isNull(userInCookie)){
            String userInfo = userInCookie.getValue();
            String[] split = userInfo.split("#");
            String username = split[0];
            String password = split[1];
            try {
                loginAndRegisterService.authenticate(username,password);
            } catch (Exception e) {
                e.printStackTrace();
                return "login";
            }
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            if (Objects.isNull(savedRequest))
                return "home";
            String requestUrl = savedRequest.getRequestUrl();
            return requestUrl;
        }
        return "login";
    }


}
