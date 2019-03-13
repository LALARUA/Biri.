package cn.zx.biri.commentservice.controller;

import cn.zx.biri.commentservice.service.ReplyService;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:17 2019/3/7 0007
 */
@Service
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @GetMapping("getReplyByCommentIdAndPageNow")
    List<ReplyEnhanced> getReplyByCommentIdAndPageNow(Integer commentId, Integer currentUserId, int pageNow){
       return replyService.getReplyByCommentIdByPageNow(commentId,currentUserId,pageNow);
    }

}
