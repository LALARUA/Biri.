package com.biri.rabbitmqservice.service;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:13 2019/2/22 0022
 */

public interface sendMessageToQueueService {
    String sendMessageToQueueDirect(String routeKey,String message);
}
