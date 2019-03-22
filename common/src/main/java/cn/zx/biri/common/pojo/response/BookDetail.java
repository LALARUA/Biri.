package cn.zx.biri.common.pojo.response;

import java.util.List;
import java.util.Set;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:51 2019/3/6 0006
 */
public class BookDetail extends BookEnhanced {
    private Integer commentPageNum;

    private List<String> catalogs;

    public List<String> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<String> catalogs) {
        this.catalogs = catalogs;
    }

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
