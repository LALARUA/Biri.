package cn.zx.biri.bookservice.mapper;



import cn.zx.biri.common.pojo.entry.Bookwithtag;
import cn.zx.biri.common.pojo.example.BookwithtagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookwithtagMapper {
    long countByExample(BookwithtagExample example);

    int deleteByExample(BookwithtagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bookwithtag record);

    int insertSelective(Bookwithtag record);

    List<Bookwithtag> selectByExample(BookwithtagExample example);

    Bookwithtag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bookwithtag record, @Param("example") BookwithtagExample example);

    int updateByExample(@Param("record") Bookwithtag record, @Param("example") BookwithtagExample example);

    int updateByPrimaryKeySelective(Bookwithtag record);

    int updateByPrimaryKey(Bookwithtag record);
}