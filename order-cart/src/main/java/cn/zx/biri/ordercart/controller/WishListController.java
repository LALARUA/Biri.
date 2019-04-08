package cn.zx.biri.ordercart.controller;

import cn.zx.biri.common.pojo.entry.WishList;
import cn.zx.biri.common.pojo.response.UserWishList;
import cn.zx.biri.ordercart.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:50 2019/4/1 0001
 */
@RestController
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @PostMapping("wishList")
    public String insertBookInWishList(@RequestBody WishList wishList, HttpServletRequest httpServletRequest, HttpSession httpSession){
        try {

            wishListService.insertBookInWishList(wishList);
        } catch (Exception e) {

        }
        return "error";
    }

    @GetMapping("wishList")
    public List<UserWishList> getWishList(Integer userId){
        try {
           return wishListService.getWishList(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
