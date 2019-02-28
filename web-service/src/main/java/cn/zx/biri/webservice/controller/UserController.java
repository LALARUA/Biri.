package cn.zx.biri.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:32 2019/2/28 0028
 */
@Controller
public class UserController {

    @GetMapping("userHome")
    public String userHome(){

        return "userHome";
    }
}
