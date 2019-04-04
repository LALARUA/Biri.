package cn.zx.biri.ordercart.service;

import cn.zx.biri.common.pojo.entry.WishList;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:47 2019/4/1 0001
 */
public interface WishListService {
    void insertBookInWishList(WishList wishList) throws Exception;
    void deleteBookInWishList(Integer wishListId) throws Exception;
}
