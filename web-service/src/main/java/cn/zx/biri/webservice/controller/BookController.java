package cn.zx.biri.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:33 2019/2/28 0028
 */
@Controller
public class BookController {

    @GetMapping("home")
    public String home(){
        return "home";
    }

}
