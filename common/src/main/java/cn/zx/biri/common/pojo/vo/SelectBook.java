package cn.zx.biri.common.pojo.vo;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:29 2019/3/8 0008
 */
public class SelectBook extends Book {

    private List<Integer> tagIds;
    private String authorName;
    private String authorId;
    private String tagName;
    private Integer pageNow;
    private Integer start;
    private List<Integer> currentBookIds;
    private Integer status;
    private String orderBy;
    private String keyword;


    @Override
    public String toString() {
        return ReflectUtils.keyByFields(this);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getCurrentBookIds() {
        return currentBookIds;
    }

    public void setCurrentBookIds(List<Integer> currentBookIds) {
        this.currentBookIds = currentBookIds;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

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

}
