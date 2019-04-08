package cn.zx.biri.ordercart.controller;

import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.UserOrder;
import cn.zx.biri.common.pojo.vo.OrderVO;
import cn.zx.biri.ordercart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:57 2019/3/28 0028
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("order")
    public String postOrder(@RequestBody OrderVO order, HttpSession httpSession){


        User user = (User) httpSession.getAttribute("user");
        order.setUserId(user.getId());
        try {
            if (orderService.postOrder(order)==-1)
                return "error";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "true";
    }

    @GetMapping("order")
    public List<UserOrder> getOrders(Integer  status,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        try {
           return orderService.getOrders(status,user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
