package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.UserOrder;
import cn.zx.biri.common.pojo.response.UserWishList;
import cn.zx.biri.webservice.feignService.OrderAndCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:32 2019/2/28 0028
 */
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private OrderAndCartService orderAndCartService;

    @GetMapping("cart")
    public String cart(HttpSession httpSession,Model model){
        User user = (User) httpSession.getAttribute("user");
        List<BookInCart> bookInCarts = orderAndCartService.bookInCarts(user.getId());
        model.addAttribute("cart",bookInCarts);

        return "cart";
    }
    @GetMapping("miniCart")
    public String miniCart(Model model, HttpSession httpSession){
        User user =(User) httpSession.getAttribute("user");
        if (user==null)
            return "commonbar::miniCartContent";
        Integer userId = user.getId();
        List<BookInCart> bookInCarts = orderAndCartService.bookInCarts(userId);
        model.addAttribute("cart",bookInCarts);
        return "commonbar::miniCartContent";
    }

    @GetMapping("order")
    public String order(Integer status, Model model){
        List<UserOrder> orders = orderAndCartService.getOrders(status);
        model.addAttribute("orders",orders);
        return "userHome::ordersTable";
    }

    @GetMapping("checkout")
    public String checkout(HttpSession httpSession,Model model){
        User user = (User) httpSession.getAttribute("user");
        List<BookInCart> bookInCarts = orderAndCartService.bookInCarts(user.getId());
        model.addAttribute("cart",bookInCarts);
        return "checkout";
    }

    @GetMapping("wishList")
    public String wishList(Model model,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<UserWishList> wishList = orderAndCartService.getWishList(user.getId());
        model.addAttribute("wishList",wishList);
        return "wishList";
    }

    @GetMapping("home")
    public String userHome(HttpSession httpSession){
        User user =(User) httpSession.getAttribute("user");
        user.getId();

        return "userHome";
    }
}
