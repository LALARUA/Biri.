package com.biri.rabbitmqservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableFeignClients
@EnableRedisHttpSession(redisFlushMode= RedisFlushMode.IMMEDIATE)
@EnableDiscoveryClient
@EnableRabbit
//@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RabbitmqServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqServiceApplication.class, args);
	}

}
