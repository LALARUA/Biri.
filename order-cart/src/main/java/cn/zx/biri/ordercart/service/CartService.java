package cn.zx.biri.ordercart.service;

import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.response.BookInCart;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:36 2019/3/24 0024
 */
public interface CartService {

    List<BookInCart> bookInCarts(Integer userId) throws Exception;
    void deleteCart(Integer cartId) throws Exception;
    void updateCart(Cart cart) throws Exception;


}
