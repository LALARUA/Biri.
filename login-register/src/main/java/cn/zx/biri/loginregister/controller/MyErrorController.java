package cn.zx.biri.loginregister.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:22 2019/2/28 0028
 */

public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){

        return "redirect:/Biri/error";
        //获取statusCode:401,404,500
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if(statusCode == 401){
//            return "error/401";
//        }else if(statusCode == 404){
//            return "error/404";
//        }else if(statusCode == 403){
//            return "error/403";
//        }else{
//            return "login";
//        }
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
