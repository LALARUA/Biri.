package cn.zx.biri.common.pojo.entry;

import java.io.Serializable;

public class ReplySupport implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer replyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}