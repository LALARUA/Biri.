package com.biri.rabbitmqservice.controller;

import com.biri.rabbitmqservice.service.sendMessageToQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:10 2019/2/22 0022
 */
@RestController
public class SendMessageToQueueController {

    @Autowired
    sendMessageToQueueService sendMessageToQueueService;

    @ResponseBody
    @PostMapping("sendMessageToQueueDirect")
    public String sendMessageToQueueDirect(String routeKey,String message){
        sendMessageToQueueService.sendMessageToQueueDirect(routeKey,message);
        return null;
    }
    @GetMapping("te")
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String id = httpServletRequest.getSession().getId();

        return "hh";
    }
}
