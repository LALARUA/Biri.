package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.example.BookExample;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;
import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.common.pojo.response.BookListDisplay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {


    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer id);

    List<BookListDisplay> selectBookListDisplayByBookId(Integer bookId);

    int insert(BookWithBLOBs record);

    int insertSelective(BookWithBLOBs record);

    List<BookWithBLOBs> selectByExampleWithBLOBs(BookExample example);

    List<Book> selectByExample(BookExample example);

    BookWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookWithBLOBs record, @Param("example") BookExample example);

    int updateByExampleWithBLOBs(@Param("record") BookWithBLOBs record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(BookWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BookWithBLOBs record);

    int updateByPrimaryKey(Book record);
}