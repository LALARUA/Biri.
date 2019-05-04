package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Tag;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:51 2019/3/6 0006
 */
public class BookDetail extends BookEnhanced {
    private Integer commentPageNum;

    private Integer commentCount;

    private List<List<Tag>> tagLinks;

    private List<String> catalogs;

    private List<BookComment> comments;

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public List<List<Tag>> getTagLinks() {
        return tagLinks;
    }

    public void setTagLinks(List<List<Tag>> tagLinks) {
        this.tagLinks = tagLinks;
    }

    public List<String> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<String> catalogs) {
        this.catalogs = catalogs;
    }



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
