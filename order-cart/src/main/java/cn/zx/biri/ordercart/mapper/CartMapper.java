package cn.zx.biri.ordercart.mapper;


import cn.zx.biri.common.pojo.entry.Cart;
import cn.zx.biri.common.pojo.example.CartExample;
import cn.zx.biri.common.pojo.response.BookInCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<BookInCart> selectBookInCart(Integer userId);
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}