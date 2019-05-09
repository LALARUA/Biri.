package cn.zx.biri.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:49 2019/5/8 0008
 */
@Controller
public class UnauthorizedController {
    @RequestMapping("unauthorized")
    public String unauthorized(Model model){
        model.addAttribute("errorMessage","权限不足~");
        return "error";
    }
}
