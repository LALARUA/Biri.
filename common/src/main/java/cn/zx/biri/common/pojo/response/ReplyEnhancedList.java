package cn.zx.biri.common.pojo.response;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:35 2019/3/7 0007
 */
public class ReplyEnhancedList {
    private Integer commentId;
    private Integer pageNum;
    List<ReplyEnhanced> replyEnhanceds;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<ReplyEnhanced> getReplyEnhanceds() {
        return replyEnhanceds;
    }

    public void setReplyEnhanceds(List<ReplyEnhanced> replyEnhanceds) {
        this.replyEnhanceds = replyEnhanceds;
    }
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
