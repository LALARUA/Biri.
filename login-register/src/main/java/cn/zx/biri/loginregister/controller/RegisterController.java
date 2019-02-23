package cn.zx.biri.loginregister.controller;

import cn.zx.biri.loginregister.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:12 2019/2/22 0022
 */
@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @ResponseBody
    @PostMapping("registerNewUser")
    public String registerNewUser(String username,String password){
        registerService.registerNewUser(username,password);
        return null;
    }
}
