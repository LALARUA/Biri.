package cn.zx.biri.ordercart;

import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.ordercart.mapper.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderCartApplicationTests {



	@Autowired
	CartMapper cartMapper;
	@Test
	public void contextLoads() {
		List<BookInCart> bookInCarts = cartMapper.selectBookInCart(4);
		Map map = new HashMap<>();

		return;
	}


}
class t{
	public t(r r){

	}
}
interface r{
	void uu(int a);
}
