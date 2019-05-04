package cn.zx.biri.ordercart.controller;

import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.UserOrder;
import cn.zx.biri.common.pojo.vo.OrderVO;
import cn.zx.biri.ordercart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("order")
    public String updateOrder(Order order){

        try {
            orderService.updateOrder(order);
        } catch (Exception e) {
            return "error";
        }
        return "success";

    }

    @GetMapping("orderToAdmin")
    public List<UserOrder> getOrdersByStatus(Integer  status){
        try {
            return orderService.getOrdersByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
