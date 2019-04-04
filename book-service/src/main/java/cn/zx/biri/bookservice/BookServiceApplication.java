package cn.zx.biri.bookservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.LinkedHashMap;

@EnableCaching
@EnableFeignClients
@EnableRedisHttpSession(redisFlushMode= RedisFlushMode.IMMEDIATE)
@EnableDiscoveryClient
@EnableRabbit
//@EnableEurekaClient
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@MapperScan("cn.zx.biri.bookservice.mapper")
public class BookServiceApplication {



	public static void main(String[] args) {
		LinkedHashMap linkedHashMap = new LinkedHashMap();


		SpringApplication.run(BookServiceApplication.class, args);
	}

}
