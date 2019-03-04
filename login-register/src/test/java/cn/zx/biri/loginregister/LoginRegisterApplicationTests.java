package cn.zx.biri.loginregister;

import cn.zx.biri.loginregister.controller.AuthenticateController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginRegisterApplicationTests {

	@Test
	public void contextLoads() {
		AuthenticateController authenticateController = new AuthenticateController();
		return;

	}

}

