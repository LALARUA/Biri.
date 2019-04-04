package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.response.UserOrder;
import cn.zx.biri.webservice.feignService.OrderAndCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:57 2019/3/25 0025
 */
@Controller
public class OrderAndCartController {

    @Autowired
    private OrderAndCartService orderAndCartService;

    @GetMapping("cart")
    public String cart(){


        return "cart";
    }

    @GetMapping("order")
    public String order(Integer status, Model model){
        List<UserOrder> orders = orderAndCartService.getOrders(status);
        model.addAttribute("orders",orders);
        return "userHome::ordersTable";
    }

    @GetMapping("checkout")
    public String checkout(){

        return "checkout";
    }

    @GetMapping("wishList")
    public String wishList(){

        return "wishList";
    }
}
