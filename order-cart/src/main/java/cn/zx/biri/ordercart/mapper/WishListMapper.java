package cn.zx.biri.ordercart.mapper;

import cn.zx.biri.common.pojo.entry.WishList;
import cn.zx.biri.common.pojo.example.WishListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishListMapper {
    long countByExample(WishListExample example);

    int deleteByExample(WishListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WishList record);

    int insertSelective(WishList record);

    List<WishList> selectByExample(WishListExample example);

    WishList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WishList record, @Param("example") WishListExample example);

    int updateByExample(@Param("record") WishList record, @Param("example") WishListExample example);

    int updateByPrimaryKeySelective(WishList record);

    int updateByPrimaryKey(WishList record);
}