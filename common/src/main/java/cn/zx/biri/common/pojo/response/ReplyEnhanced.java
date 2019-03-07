package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Reply;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 20:20 2019/3/6 0006
 */
public class ReplyEnhanced extends Reply {

    private String toUsername;
    private String fromUsername;
    private Integer isCurrentUserSupport;
    private Integer supports;
    private String showDatetime;

    public Integer getSupports() {
        return supports;
    }

    public void setSupports(Integer supports) {
        this.supports = supports;
    }

    public String getShowDatetime() {
        return showDatetime;
    }

    public void setShowDatetime(String showDatetime) {
        this.showDatetime = showDatetime;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Integer getIsCurrentUserSupport() {
        return isCurrentUserSupport;
    }

    public void setIsCurrentUserSupport(Integer currentUserIsSupport) {
        this.isCurrentUserSupport = currentUserIsSupport;
    }
}
