package com.biri.rabbitmqservice.service;

import cn.zx.biri.common.commonBean.Regex;
import com.biri.rabbitmqservice.config.MyProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;


import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:34 2019/2/22 0022
 */
@Service
@EnableConfigurationProperties(MyProperties.class)
public class ListenerService {

    @Autowired
    MyProperties myProperties;
    @Autowired
    JavaMailSender javaMailSender;

    @RabbitListener(queues = "airi.registerNewUser")
    public void registerNewUser(String username){

        //邮箱
        if (username.matches(Regex.EMAIL.toString())) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("");
            simpleMailMessage.setText(username + myProperties.getRegisterMessage());
            simpleMailMessage.setTo(username);
            simpleMailMessage.setFrom("745402208@qq.com");
            javaMailSender.send(simpleMailMessage);
        }
        //手机
        else if (username.matches(Regex.PHONE.toString())){

        }
    }
    @RabbitListener(queues = "airi.CAPTCHA")
    public void getCAPTCHA(String message){
        String[] split = message.split("#");
        String username = split[0];
        String CAPTCHA = split[1];
        String emailRegex = Regex.EMAIL.toString();
        String phoneRegex = Regex.PHONE.toString();
        if (username.matches(emailRegex)) {
            String text = myProperties.getGetCAPTCHAMessage()+CAPTCHA;
            sendMail(username,text);
            return;
        }


        //手机
        else if (username.matches(phoneRegex)){

        }
    }

    public void sendMail(String to,String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("");
        simpleMailMessage.setText(text);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("745402208@qq.com");
        javaMailSender.send(simpleMailMessage);
    }


}
