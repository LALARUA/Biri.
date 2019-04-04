package cn.zx.biri.webservice.feignService;

import cn.zx.biri.common.pojo.response.UserOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 12:53 2019/4/3 0003
 */

@FeignClient("order-cart")
public interface OrderAndCartService {

    @GetMapping("order")
    List<UserOrder> getOrders(@RequestParam("status") Integer  status);
}
