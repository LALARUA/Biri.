package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Comment;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:04 2019/3/6 0006
 */
public class BookComment extends Comment {
    private String username;
    private Integer supports;
    private Integer isCurrentUserSupport;
    private ReplyEnhancedList replyEnhancedList;

    public ReplyEnhancedList getReplyEnhancedList() {
        return replyEnhancedList;
    }

    public void setReplyEnhancedList(ReplyEnhancedList replyEnhancedList) {
        this.replyEnhancedList = replyEnhancedList;
    }

    private String showDatetime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShowDatetime() {
        return showDatetime;
    }

    public void setShowDatetime(String showDatetime) {
        this.showDatetime = showDatetime;
    }

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public Integer getIsCurrentUserSupport() {
        return isCurrentUserSupport;
    }

    public void setIsCurrentUserSupport(Integer isCurrentUserSupport) {
        this.isCurrentUserSupport = isCurrentUserSupport;
    }


}
