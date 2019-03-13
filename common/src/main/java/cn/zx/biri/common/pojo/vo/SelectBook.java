package cn.zx.biri.common.pojo.vo;

import cn.zx.biri.common.pojo.entry.Book;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:29 2019/3/8 0008
 */
public class SelectBook extends Book {
    private String authorName;
    private String authorId;
    private String tagName;
    private String tagId;
    private Integer pageNow;
    private Integer start;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
