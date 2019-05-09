package cn.zx.biri.zuul.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:22 2019/2/28 0028
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        String flag = (String)request.getAttribute("flag");

        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 401){

        }else if(statusCode == 404){
            model.addAttribute("errorMessage","这个页面丢了");

        }else if(statusCode == 403){

        }else{
            model.addAttribute("errorMessage","好像出了点什么错~");

        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
