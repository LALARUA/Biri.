package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.entry.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:32 2019/2/28 0028
 */
@RequestMapping("user")
@Controller
public class UserController {

    @GetMapping("home")
    public String userHome(HttpSession httpSession){
        User user =(User) httpSession.getAttribute("user");
        user.getId();

        return "userHome";
    }
}
