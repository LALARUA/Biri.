package cn.zx.biri.ordercart.service.serviceImpl;

import cn.zx.biri.common.pojo.entry.WishList;
import cn.zx.biri.common.pojo.response.UserWishList;
import cn.zx.biri.ordercart.mapper.WishListMapper;
import cn.zx.biri.ordercart.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:47 2019/4/1 0001
 */
@Service
public class WishLIstServiceImpl implements WishListService {
    @Autowired
    private WishListMapper wishListMapper;
    @Override
    public void insertBookInWishList(WishList wishList) throws Exception {

        wishListMapper.insert(wishList);
    }

    @Override
    public void deleteBookInWishList(Integer wishListId) throws Exception {
        wishListMapper.deleteByPrimaryKey(wishListId);
    }

    @Override
    public List<UserWishList> getWishList(Integer userId) throws Exception {
       return wishListMapper.getWishList(userId);
    }
}
