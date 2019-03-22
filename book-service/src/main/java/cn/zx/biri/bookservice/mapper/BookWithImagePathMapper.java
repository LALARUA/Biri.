package cn.zx.biri.bookservice.mapper;


import cn.zx.biri.common.pojo.entry.BookWithImagePath;
import cn.zx.biri.common.pojo.example.BookWithImagePathExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookWithImagePathMapper {
    long countByExample(BookWithImagePathExample example);

    int deleteByExample(BookWithImagePathExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookWithImagePath record);

    int insertSelective(BookWithImagePath record);

    List<BookWithImagePath> selectByExample(BookWithImagePathExample example);

    BookWithImagePath selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookWithImagePath record, @Param("example") BookWithImagePathExample example);

    int updateByExample(@Param("record") BookWithImagePath record, @Param("example") BookWithImagePathExample example);

    int updateByPrimaryKeySelective(BookWithImagePath record);

    int updateByPrimaryKey(BookWithImagePath record);
}