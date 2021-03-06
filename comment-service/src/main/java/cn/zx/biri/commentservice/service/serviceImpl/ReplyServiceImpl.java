package cn.zx.biri.commentservice.service.serviceImpl;

import cn.zx.biri.commentservice.mapper.ReplyMapper;
import cn.zx.biri.commentservice.mapper.ReplySupportMapper;
import cn.zx.biri.commentservice.service.ReplyService;
import cn.zx.biri.common.pojo.entry.Reply;
import cn.zx.biri.common.pojo.entry.ReplySupport;
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
public class ReplyServiceImpl implements ReplyService {
    private final int pageSize = 3;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ReplySupportMapper replySupportMapper;


    @Override
    public List<ReplyEnhancedList> getRepliesByCommentId(List<Integer> commentIds,Integer currentUserId){
        Map map = new HashMap();
        map.put("commentIds",commentIds);
        map.put("currentUserId",currentUserId);
        map.put("pageSize",3);
        List<ReplyEnhancedList> replyByCommentId = replyMapper.getRepliesByCommentId(map);
        for (ReplyEnhancedList replies : replyByCommentId) {
            ReplyExample replyExample = new ReplyExample();
            replyExample.createCriteria().andCommentIdEqualTo(replies.getCommentId());
            long replyCount = replyMapper.countByExample(replyExample);
            int pageNum = (int)(replyCount-1)/pageSize+1;
            replies.setPageNum(pageNum);
        }
        return replyByCommentId;
    }

    @Override
    public List<ReplyEnhanced> getReplyByCommentIdByPageNow(Integer commentId,Integer currentUserId,int pageNow){
        Map map = new HashMap();
        map.put("commentId",commentId);
        map.put("currentUserId",currentUserId);
        map.put("start",(pageNow-1)*pageSize);
        map.put("pageSize",pageSize);
        return replyMapper.getReplyByCommentIdAndPageNow(map);
    }

    @Override
    public void submitReply(Reply reply) throws Exception{
        replyMapper.insertSelective(reply);
    }

    @Override
    public void replySupport(ReplySupport replySupport) {
        replySupportMapper.insertSelective(replySupport);
    }


}
