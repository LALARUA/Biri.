package cn.zx.biri.ordercart.service;

import cn.zx.biri.common.pojo.entry.WishList;
import cn.zx.biri.common.pojo.response.UserWishList;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:47 2019/4/1 0001
 */
public interface WishListService {
    int q = 5;
    void insertBookInWishList(WishList wishList) throws Exception;
    void deleteBookInWishList(Integer wishListId) throws Exception;
    List<UserWishList> getWishList(Integer userId) throws Exception;
}
