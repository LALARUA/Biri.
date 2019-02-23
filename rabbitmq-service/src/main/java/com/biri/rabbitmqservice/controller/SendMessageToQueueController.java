package com.biri.rabbitmqservice.controller;

import com.biri.rabbitmqservice.service.sendMessageToQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
