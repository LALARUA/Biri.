package cn.zx.biri.commentservice.mapper;


import cn.zx.biri.common.pojo.entry.Reply;
import cn.zx.biri.common.pojo.example.ReplyExample;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import cn.zx.biri.common.pojo.response.ReplyEnhancedList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {

    List<ReplyEnhanced> getReplyByCommentIdAndPageNow(Map map);

    List<ReplyEnhancedList> getRepliesByCommentId(Map map);

    long countByExample(ReplyExample example);

    int deleteByExample(ReplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}