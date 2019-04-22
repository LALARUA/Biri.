package cn.zx.biri.ordercart.service;

import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.response.UserOrder;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:59 2019/3/28 0028
 */
public interface OrderService {
    int postOrder(Order order) throws Exception;
    List<UserOrder> getOrders(Integer status,Integer userId) throws Exception;
    List<UserOrder> getOrdersByStatus(Integer status) throws Exception;
}
