package cn.zx.biri.commentservice.service;

import cn.zx.biri.commentservice.mapper.ReplyMapper;
import cn.zx.biri.common.pojo.entry.Reply;
import cn.zx.biri.common.pojo.example.ReplyExample;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import cn.zx.biri.common.pojo.response.ReplyEnhancedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:20 2019/3/7 0007
 */
@Service
public class ReplyService {
    private final int pageSize = 5;

    @Autowired
    ReplyMapper replyMapper;


    public List<ReplyEnhancedList> getRepliesByCommentId(List<Integer> commentIds,Integer currentUserId){
        Map map = new HashMap();
        map.put("commentIds",commentIds);
        map.put("currentUserId",currentUserId);
        List<ReplyEnhancedList> replyByCommentId = replyMapper.getRepliesByCommentId(map);
        for (ReplyEnhancedList replies : replyByCommentId) {
            ReplyExample replyExample = new ReplyExample();
            replyExample.createCriteria().andCommentIdEqualTo(replies.getCommentId());
            long repliesCount = replyMapper.countByExample(replyExample);
            int pageNum = (int)(repliesCount-1)/pageSize+1;
            replies.setPageNum(pageNum);
        }
        return null;
    }
    public List<ReplyEnhanced> getReplyByCommentIdAndPageNow(Integer commentId,Integer currentUserId,int pageNow){

        Map map = new HashMap();
        map.put("commentId",commentId);
        map.put("currentUserId",currentUserId);
        map.put("start",pageNow*pageSize+1);
        map.put("pageSize",pageSize);
        return replyMapper.getReplyByCommentIdAndPageNow(map);
    }


}
