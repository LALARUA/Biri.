package cn.zx.biri.commentservice.controller;

import cn.zx.biri.commentservice.service.ReplyService;
import cn.zx.biri.common.pojo.entry.CommentSupport;
import cn.zx.biri.common.pojo.entry.Reply;
import cn.zx.biri.common.pojo.entry.ReplySupport;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import cn.zx.biri.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:17 2019/3/7 0007
 */
@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("reply")
    public List<ReplyEnhanced> getReplyByCommentIdAndPageNow(Integer commentId, Integer currentUserId, int pageNow){
       return replyService.getReplyByCommentIdByPageNow(commentId,currentUserId,pageNow);
    }

    @PostMapping("reply")
    public String submitReply(@RequestBody Reply reply){
        try {
            reply.setDatetime(DateUtils.dateToString(new Date()));
            replyService.submitReply(reply);
            return "提交成功";
        } catch (Exception e) {
            return "提交错误";
        }
    }

    @PostMapping("replySupport")
    public String commentSupport(ReplySupport replySupport){
        try {
            replyService.replySupport(replySupport);
            return "suucess";
        } catch (Exception e) {
            return "error";
        }
    }
}
