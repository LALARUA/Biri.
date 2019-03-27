package cn.zx.biri.ordercart.service.serviceImpl;

import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.ordercart.mapper.CartMapper;
import cn.zx.biri.ordercart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:37 2019/3/24 0024
 */
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<BookInCart> bookInCarts(Integer userId) throws Exception {
        return cartMapper.selectBookInCart(userId);
    }

    @Override
    public void deleteCart(Integer cartId) throws Exception {
        cartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void updateCart(Cart cart) throws Exception {
        cartMapper.updateByPrimaryKeySelective(cart);
    }
}
