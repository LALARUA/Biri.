package cn.zx.biri.ordercart.controller;

import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.ordercart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    @PostMapping("cart")
    public String insertBookInCart(@RequestBody BookInCart bookInCart, HttpSession httpSession, HttpServletRequest httpServletRequest){
        User user =(User) httpSession.getAttribute("user");
        if (user==null){
            String referer = httpServletRequest.getHeader("referer");
            try {
                referer = URLDecoder.decode(referer,"utf-8");
                referer= referer.substring(referer.indexOf("/Biri"));
                httpSession.setAttribute("backUrl",referer);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "error";
            }
            return "mustLogin";
        }
        try {
            Cart cart = new Cart();
            cart.setBookId(bookInCart.getId());
            cart.setNumber(bookInCart.getBookNum());
            cart.setUserId(user.getId());

            cartService.insertBookInCart(cart);
//            bookInCart.setCartId(cart.getId());
//            Map<Integer, BookInCart> collect = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
//            collect.put(bookInCart.getCartId(),bookInCart);
//            httpSession.setAttribute("cart",collect);
            return String.valueOf(cart.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @DeleteMapping("cart")
    public String deleteBookInCarts(Integer cartId, HttpSession httpSession){
        try {
            cartService.deleteCart(cartId);
//            Map<Integer,BookInCart> cart = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
//            cart.remove(cartId);
//            httpSession.setAttribute("cart",cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("cart")
    public String updateBookInCarts(Cart cart){
        try {
            cartService.updateCart(cart);
//            Map<Integer,BookInCart> cartInSession = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
//            BookInCart bookInCart = cartInSession.get(cart.getId());
//            bookInCart.setBookNum(cart.getNumber());
//            cartInSession.put(cart.getId(),bookInCart);
//            httpSession.setAttribute("cart",cartInSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
