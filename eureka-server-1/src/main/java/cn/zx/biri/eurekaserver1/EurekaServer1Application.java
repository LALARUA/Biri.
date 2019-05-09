package cn.zx.biri.eurekaserver1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.redis.core.RedisTemplate;

@EnableEurekaServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,RedisAutoConfiguration.class, RabbitAutoConfiguration.class})
public class EurekaServer1Application {

	public static void main(String[] args) {

		SpringApplication.run(EurekaServer1Application.class, args);
	}

}

