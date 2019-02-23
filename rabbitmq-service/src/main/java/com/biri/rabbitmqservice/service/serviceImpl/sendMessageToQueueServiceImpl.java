package com.biri.rabbitmqservice.service.serviceImpl;

import com.biri.rabbitmqservice.service.sendMessageToQueueService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:14 2019/2/22 0022
 */
@Service
public class sendMessageToQueueServiceImpl implements sendMessageToQueueService {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Override
    public String sendMessageToQueueDirect(String routeKey, String message) {
        rabbitTemplate.convertAndSend("exchange.direct",routeKey,message);
        return null;
    }
}
