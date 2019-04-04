package cn.zx.biri.common.pojo.vo;

import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.response.BookInCart;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:05 2019/3/28 0028
 */
public class OrderVO extends Order {
    private Map<Integer,BookInCart> orderDetails;

    public Map<Integer, BookInCart> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Integer, BookInCart> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
