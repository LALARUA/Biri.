package cn.zx.biri.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:57 2019/3/25 0025
 */
@Controller
public class OrderAndCartController {

    @GetMapping("cart")
    public String Cart(){

        return "cart";
    }
}
