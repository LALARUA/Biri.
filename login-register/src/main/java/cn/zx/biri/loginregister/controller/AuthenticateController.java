package cn.zx.biri.loginregister.controller;


import cn.zx.biri.common.commonBean.RegEx;
import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.vo.LoginVO;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;
import cn.zx.biri.loginregister.feignService.RabbitmqService;
import cn.zx.biri.loginregister.feignService.UserService;
import cn.zx.biri.loginregister.service.AuthenticateService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:52 2019/1/10 0010
 */

@RestController
public class AuthenticateController {
    @Autowired
    AuthenticateService authenticateService;
//
    @Autowired
    RabbitmqService rabbitmqService;
//
    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SessionRepository sessionRepository;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

    @PostMapping("authenticate")
    public Map authenticate(@Valid LoginVO loginVO, BindingResult bindingResult, HttpServletRequest httpServletRequest, HttpSession httpSession){
        Map map = new HashMap();

        if(bindingResult.hasErrors()){
            map.put("errorMessage",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            authenticateService.authenticate(loginVO);
            String backUrl;
            if ((backUrl=(String) httpSession.getAttribute("backUrl")) != null){
                map.put("url",backUrl);
                httpSession.removeAttribute("backUrl");
            }else {
                SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
                if (Objects.isNull(savedRequest))
                    map.put("url","/Biri/home");
                else
                    map.put("url",savedRequest.getRequestUrl());
            }

        } catch (IncorrectCredentialsException e) {
            map.put("errorMessage","密码错误");
            logger.info("密码错误");
        } catch (UnknownAccountException e){
            map.put("errorMessage","该用户不存在");
            logger.info("该用户不存在");
        } catch (Exception e){
            e.printStackTrace();
            map.put("errorMessage","登录失败,请稍后再试");
            logger.info("登录失败");
        }
        return map;
    }
    @GetMapping("logout")
    public void logout(HttpSession httpSession){
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        String sessionId = httpSession.getId();
        sessionRepository.deleteById(sessionId);
        redisTemplate.delete("spring:session:sessions:"+sessionId);

    }
    @PostMapping("register")
    public Map register(@Valid RegisterAndChangePasswordVO registerVO, BindingResult bindingResult,HttpServletRequest httpServletRequest){
        Map map = new HashMap();
        if(bindingResult.hasErrors()){
            map.put("errorMessage",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            User user = userService.selectUserByUsername(registerVO.getUsername());
            if (user!=null){
                map.put("errorMessage","此用户已被注册");
                return map;
            }
            authenticateService.registerNewUser(registerVO);
//          Session byId = sessionRepository.findById(session.getId());
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            if (Objects.isNull(savedRequest))
                map.put("url","home");
            else
                map.put("url",savedRequest.getRequestUrl());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("errorMessage","注册失败");
        }
        return map;
    }
    @PostMapping("changePassword")
    public Map changePassword(@Valid RegisterAndChangePasswordVO changePasswordVO, BindingResult bindingResult){
        Map map = new HashMap();
        if(bindingResult.hasErrors()){
            map.put("errorMessage",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        try {
            userService.updateUserPassword(changePasswordVO);
        } catch (Exception e) {
            map.put("errorMessage","修改密码失败");
        }
        return map;

    }
    @GetMapping("getCAPTCHA")
    public Map getCAPTCHA(String username,Integer flag){
        Map map = new HashMap<>();
        if (!username.matches(RegEx.EMAIL.toString())){
            map.put("errorMessage","请输入正确的邮箱");
        }
        try {
            User user = userService.selectUserByUsername(username);
            if (user!=null&&flag==0){
                map.put("errorMessage","此用户已被注册");
                return map;
            }
            if (user==null&&flag==3){
                map.put("errorMessage","此用户还未注册");
                return map;
            }
            Integer CAPTCHA = new Random().nextInt(9000) + 1000;
            String message = username+"#"+CAPTCHA;
            map.put("CAPTCHA",CAPTCHA);
            rabbitmqService.sendMessageToQueueDirect("getCAPTCHA",message);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errorMessage","获取验证码失败");
            return map;
        }
    }
}
