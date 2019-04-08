package cn.zx.biri.ordercart.service.serviceImpl;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.entry.OrderDetail;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.UserOrder;
import cn.zx.biri.common.pojo.vo.OrderVO;
import cn.zx.biri.common.utils.DateUtils;
import cn.zx.biri.ordercart.feignService.BookService;
import cn.zx.biri.ordercart.mapper.OrderDetailMapper;
import cn.zx.biri.ordercart.mapper.OrderMapper;
import cn.zx.biri.ordercart.service.CartService;
import cn.zx.biri.ordercart.service.OrderService;
import cn.zx.biri.ordercart.service.WishListService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:59 2019/3/28 0028
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    CartService cartService;


    @Transactional
    @Override
    public int postOrder(Order order) throws Exception {
        if (bookService.reduceStock() == -1)
            return -1;


        order.setCreatetime(DateUtils.dateToString(new Date()));
        orderMapper.insertSelective(order);

        List<BookInCart> bookInCarts = cartService.bookInCarts(order.getUserId());
        Map<Integer, BookInCart> cart = bookInCarts.stream().collect(Collectors.toMap(BookInCart::getCartId, (p) -> p));
        ((OrderVO)order).setOrderDetails(cart);
        int orderId = order.getId();
        Map<Integer, BookInCart> orderDetails = ((OrderVO) order).getOrderDetails();
        for (Map.Entry<Integer,BookInCart> entry : orderDetails.entrySet()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBookId(entry.getValue().getId());
            orderDetail.setBookNum(entry.getValue().getBookNum());
            orderDetail.setOrderId(orderId);
            orderDetail.setSummoney(entry.getValue().getPrice()*entry.getValue().getBookNum());
            orderDetailMapper.insertSelective(orderDetail);
        }

        return 0;
    }

    @Override
    public List<UserOrder> getOrders(int status,int userId) throws Exception {
        Map<String,Integer> map = new HashMap<>();

        map.put("userId",userId);
        map.put("status",status);
        List<UserOrder> orders = orderMapper.getOrders(map);

        return orders;
    }
}
