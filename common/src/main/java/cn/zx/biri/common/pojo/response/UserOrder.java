package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.entry.OrderDetail;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:04 2019/3/28 0028
 */
public class UserOrder extends Order {
    private List<UserOrderDetail> orderDetails;

    public List<UserOrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<UserOrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
