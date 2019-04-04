package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.example.BookExample;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookMapper {

    @Update("update book set keyword = #{keyword} where id = #{id}")
    void setKeyWord(@Param("keyword")  String keyword,@Param("id")int id);

    List<String> filterPublisher(List<Integer> bookIds);
    List<Tag> filterTag(List<Integer> bookIds);
    List<Author> filterAuthor(List<Integer> bookIds);

    List<Integer> selectBookCount(SelectBook selectBook);

    BookWithBLOBs getBookForUpdate(Integer bookId);

    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer id);

    BookDetail selectBookDetail(Integer bookId);

    List<BookEnhanced> selectBookList(SelectBook condition);

    Integer selectBookCountByCondition(SelectBook condition);

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