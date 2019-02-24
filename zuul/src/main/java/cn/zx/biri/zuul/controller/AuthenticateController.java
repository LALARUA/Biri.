package cn.zx.biri.zuul.controller;


import cn.zx.biri.common.pojo.User;
import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.zuul.feignService.RabbitmqService;
import cn.zx.biri.zuul.feignService.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import cn.zx.biri.zuul.service.AuthenticateService;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:52 2019/1/10 0010
 */
@Controller
public class AuthenticateController {
    @Autowired
    AuthenticateService authenticateService;
//
    @Autowired
    RabbitmqService rabbitmqService;
//
    @Autowired
    UserService userService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

    @ResponseBody
    @GetMapping("test")
    public String test(){
        System.out.println("ss");
        return "hello world";
    }

    @GetMapping("loginPage")
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
                authenticateService.authenticate(username,password);
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



    @ResponseBody
    @PostMapping("authenticate")
    public Map authenticate(@Valid User user, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        Map map = new HashMap();
        map.put("flag",1);
        if(bindingResult1.hasErrors()){
            map.put("errorMessage",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }

        String username = user.getUsername();
        String password = user.getPassword();
        try {
            authenticateService.authenticate(username,password);
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            if (Objects.isNull(savedRequest))
                map.put("url","index");
            else
                map.put("url",savedRequest.getRequestUrl());
            map.put("flag",0);
        } catch (IncorrectCredentialsException e) {
            map.put("errorMessage","密码错误");
            logger.info("密码错误");

        } catch (UnknownAccountException e){
            map.put("errorMessage","该用户不存在");
            logger.info("该用户不存在");

        } catch (Exception e){
            map.put("errorMessage","登录失败,请稍后再试");
            map.put("flag",1);
            logger.info("登录失败");
        }
        return map;
    }

    @ResponseBody
    @PostMapping("register")
    public Map register(String username,String password){

        Map map = new HashMap();
        return map;
    }

    @ResponseBody
    @GetMapping("getCAPTCHA")
    public Map getCAPTCHA(String username){
        Map map = new HashMap<>();
        try {
//            User user = userService.selectUserByUsername(username);
            User user = null;
            if (user!=null){
                map.put("flag",1);
                map.put("errorMessage","此用户已被注册");
                return map;
            }

            Integer CAPTCHA = new Random().nextInt(9000) + 1000;
            String message = username+"#"+CAPTCHA;
            map.put("flag",0);
            map.put("CAPTCHA",CAPTCHA);

        rabbitmqService.sendMessageToQueueDirect("getCAPTCHA",message);
            return map;
        } catch (Exception e) {
            map.put("flag",1);
            map.put("errorMessage","获取验证码失败");
            map.put("CAPTCHA","");
            return map;
        }
    }
//
//    @PostMapping("register")
//    public String register(String username,String password){
//        userService.addNewUser(username,password);
//        return "index";
//    }



}
