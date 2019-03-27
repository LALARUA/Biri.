package cn.zx.biri.loginregister.feignService;

import cn.zx.biri.common.pojo.response.BookInCart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:31 2019/3/24 0024
 */
@FeignClient(value = "order-cart")
public interface OrderAndCartService {

    @GetMapping("cart")
    List<BookInCart> bookInCarts(@RequestParam("userId") Integer userId);
}
