package cn.zx.biri.bookservice.mapper;

import cn.zx.biri.common.pojo.Entry.Author;
import cn.zx.biri.common.pojo.Entry.AuthorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorMapper {
    long countByExample(AuthorExample example);

    int deleteByExample(AuthorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Author record);

    int insertSelective(Author record);

    List<Author> selectByExampleWithBLOBs(AuthorExample example);

    List<Author> selectByExample(AuthorExample example);

    Author selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Author record, @Param("example") AuthorExample example);

    int updateByExampleWithBLOBs(@Param("record") Author record, @Param("example") AuthorExample example);

    int updateByExample(@Param("record") Author record, @Param("example") AuthorExample example);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKeyWithBLOBs(Author record);

    int updateByPrimaryKey(Author record);
}