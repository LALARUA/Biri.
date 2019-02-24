package com.biri.rabbitmqservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:34 2019/2/22 0022
 */
@ConfigurationProperties(prefix = "rabbitmqservice")
public class MyProperties {
    private String directExchangeName;
    private String registerMessage;
    private String getCAPTCHAMessage;

    public String getGetCAPTCHAMessage() {
        return getCAPTCHAMessage;
    }

    public void setGetCAPTCHAMessage(String getCAPTCHAMessage) {
        this.getCAPTCHAMessage = getCAPTCHAMessage;
    }

    public String getRegisterMessage() {
        return registerMessage;
    }

    public void setRegisterMessage(String registerMessage) {
        this.registerMessage = registerMessage;
    }

    public String getDirectExchangeName() {
        return directExchangeName;
    }

    public void setDirectExchangeName(String directExchangeName) {
        this.directExchangeName = directExchangeName;
    }


}
