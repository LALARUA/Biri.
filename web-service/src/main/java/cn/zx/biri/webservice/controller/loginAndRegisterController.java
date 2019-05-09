package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.webservice.feignService.LoginAndRegisterService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
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
    public String login(HttpServletRequest httpServletRequest, HttpSession httpSession){
        if (httpSession.getAttribute("user")!=null){
            return "redirect:http://localhost:8769/Biri/home";
        }
        String cookie = httpServletRequest.getHeader("Cookie");
        String id = httpSession.getId();
        Cookie userInCookie = CookieUtils.getCookieByName(httpServletRequest,"user");
        if (!Objects.isNull(userInCookie)){
            String userInfo = userInCookie.getValue();
            String[] split = userInfo.split("#");
            String username = split[0];
            String password = split[1];
            try {
                Map authenticate = loginAndRegisterService.authenticate(username, password);
                String url = (String) authenticate.get("url");
                return "redirect:http://localhost:8769"+url;
            } catch (Exception e) {
                e.printStackTrace();
                return "login";
            }

        }
        return "login";
    }


}
