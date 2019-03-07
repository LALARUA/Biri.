package cn.zx.biri.commentservice.mapper;

import cn.zx.biri.common.pojo.entry.ReplySupport;
import cn.zx.biri.common.pojo.example.ReplySupportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplySupportMapper {
    long countByExample(ReplySupportExample example);

    int deleteByExample(ReplySupportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReplySupport record);

    int insertSelective(ReplySupport record);

    List<ReplySupport> selectByExample(ReplySupportExample example);

    ReplySupport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReplySupport record, @Param("example") ReplySupportExample example);

    int updateByExample(@Param("record") ReplySupport record, @Param("example") ReplySupportExample example);

    int updateByPrimaryKeySelective(ReplySupport record);

    int updateByPrimaryKey(ReplySupport record);
}