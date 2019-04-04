package cn.zx.biri.ordercart.mapper;


import cn.zx.biri.common.pojo.entry.Order;
import cn.zx.biri.common.pojo.example.OrderExample;
import cn.zx.biri.common.pojo.response.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    List<UserOrder> getOrders(Map<String,Integer> map);

    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}