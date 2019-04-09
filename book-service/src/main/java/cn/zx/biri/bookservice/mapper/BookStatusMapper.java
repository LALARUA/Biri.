package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.BookStatus;
import cn.zx.biri.common.pojo.example.BookStatusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookStatusMapper {
    long countByExample(BookStatusExample example);

    int deleteByExample(BookStatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookStatus record);

    int insertSelective(BookStatus record);

    List<BookStatus> selectByExample(BookStatusExample example);

    BookStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookStatus record, @Param("example") BookStatusExample example);

    int updateByExample(@Param("record") BookStatus record, @Param("example") BookStatusExample example);

    int updateByPrimaryKeySelective(BookStatus record);

    int updateByPrimaryKey(BookStatus record);
}