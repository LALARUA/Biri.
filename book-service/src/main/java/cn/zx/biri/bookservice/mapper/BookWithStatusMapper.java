package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.BookWithStatus;
import cn.zx.biri.common.pojo.example.BookWithStatusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookWithStatusMapper {
    long countByExample(BookWithStatusExample example);

    int deleteByExample(BookWithStatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookWithStatus record);

    int insertSelective(BookWithStatus record);

    List<BookWithStatus> selectByExample(BookWithStatusExample example);

    BookWithStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookWithStatus record, @Param("example") BookWithStatusExample example);

    int updateByExample(@Param("record") BookWithStatus record, @Param("example") BookWithStatusExample example);

    int updateByPrimaryKeySelective(BookWithStatus record);

    int updateByPrimaryKey(BookWithStatus record);
}