package cn.zx.biri.common.pojo.response;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:51 2019/3/6 0006
 */
public class BookDetail extends BookEnhanced {
    private Integer commentPageNum;


    private List<BookComment> comments;

    public Integer getCommentPageNum() {
        return commentPageNum;
    }

    public void setCommentPageNum(Integer commentPageNum) {
        this.commentPageNum = commentPageNum;
    }

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }
}
