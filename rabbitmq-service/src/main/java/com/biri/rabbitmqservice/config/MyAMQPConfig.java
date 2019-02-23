package com.biri.rabbitmqservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:25 2019/2/22 0022
 */
@Configuration
public class MyAMQPConfig {
    /**
     * @description 修改序列化为json
     * @author xiangXX
     * @date 2019/2/22 0022 14:26
      * @param
     *
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
