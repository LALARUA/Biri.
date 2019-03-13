package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.BookWithAuthor;
import cn.zx.biri.common.pojo.example.BookWithAuthorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookWithAuthorMapper {
    long countByExample(BookWithAuthorExample example);

    int deleteByExample(BookWithAuthorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookWithAuthor record);

    int insertSelective(BookWithAuthor record);

    List<BookWithAuthor> selectByExample(BookWithAuthorExample example);

    BookWithAuthor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookWithAuthor record, @Param("example") BookWithAuthorExample example);

    int updateByExample(@Param("record") BookWithAuthor record, @Param("example") BookWithAuthorExample example);

    int updateByPrimaryKeySelective(BookWithAuthor record);

    int updateByPrimaryKey(BookWithAuthor record);
}