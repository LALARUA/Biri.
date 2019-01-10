package cn.zx.biri.loginregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(redisFlushMode=RedisFlushMode.IMMEDIATE)
//@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LoginRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterApplication.class, args);

	}

}

