package cn.zx.biri.commentservice.service;

import cn.zx.biri.common.pojo.entry.Reply;
import cn.zx.biri.common.pojo.entry.ReplySupport;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import cn.zx.biri.common.pojo.response.ReplyEnhancedList;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:19 2019/3/7 0007
 */
public interface ReplyService {
    List<ReplyEnhancedList> getRepliesByCommentId(List<Integer> commentIds, Integer currentUserId);
    List<ReplyEnhanced> getReplyByCommentIdByPageNow(Integer commentId, Integer currentUserId, int pageNow);
    void submitReply(Reply reply) throws Exception;
    void replySupport(ReplySupport replySupport);
}
