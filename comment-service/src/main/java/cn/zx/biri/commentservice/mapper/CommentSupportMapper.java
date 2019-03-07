package cn.zx.biri.commentservice.mapper;


import cn.zx.biri.common.pojo.entry.CommentSupport;
import cn.zx.biri.common.pojo.example.CommentSupportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentSupportMapper {
    long countByExample(CommentSupportExample example);

    int deleteByExample(CommentSupportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentSupport record);

    int insertSelective(CommentSupport record);

    List<CommentSupport> selectByExample(CommentSupportExample example);

    CommentSupport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentSupport record, @Param("example") CommentSupportExample example);

    int updateByExample(@Param("record") CommentSupport record, @Param("example") CommentSupportExample example);

    int updateByPrimaryKeySelective(CommentSupport record);

    int updateByPrimaryKey(CommentSupport record);
}