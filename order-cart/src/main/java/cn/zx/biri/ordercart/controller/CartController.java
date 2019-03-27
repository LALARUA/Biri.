package cn.zx.biri.ordercart.controller;

import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.ordercart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:16 2019/3/24 0024
 */
@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("cart")
    public List<BookInCart> selectBookInCarts(Integer userId){

        try {
          return cartService.bookInCarts(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @DeleteMapping("cart")
    public String deleteBookInCarts(Integer cartId, HttpSession httpSession){
        try {
            cartService.deleteCart(cartId);
            Map<Integer,BookInCart> cart = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
            cart.remove(cartId);
            httpSession.setAttribute("cart",cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("cart")
    public String updateBookInCarts(Cart cart,HttpSession httpSession){
        try {
            cartService.updateCart(cart);
            Map<Integer,BookInCart> cartInSession = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
            BookInCart bookInCart = cartInSession.get(cart.getBookId());
            bookInCart.setBookNum(cart.getNumber());
            cartInSession.put(cart.getId(),bookInCart);
            httpSession.setAttribute("cart",cartInSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
