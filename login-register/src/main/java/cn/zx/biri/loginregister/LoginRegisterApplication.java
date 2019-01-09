package cn.zx.biri.loginregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableEurekaClient
@SpringBootApplication
public class LoginRegisterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LoginRegisterApplication.class, args);
		System.out.println("ts2"+run.getBean("ts2"));
        System.out.println("ts3"+run.getBean("ts3"));
	}

}

