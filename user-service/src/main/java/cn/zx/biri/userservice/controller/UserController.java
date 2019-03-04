package cn.zx.biri.userservice.controller;


import cn.zx.biri.common.pojo.Entry.User;
import cn.zx.biri.common.pojo.VO.LoginVO;
import cn.zx.biri.common.pojo.VO.RegisterAndChangePasswordVO;
import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:49 2019/2/28 0028
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("authenticate")
    public User authenticate(String username,String password){

        return null;
    }

    @GetMapping("updateUser")
    public void updateUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO){
        return;
    }

    @GetMapping("selectUserByUsername")
    public User selectUserByUsername(@RequestParam("username") String username){
       return userService.selectUserByUsername(username);
    }

    @PostMapping("insertUser")
    public void insertUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO){
        userService.insertUser(registerAndChangePasswordVO);
    }


}
