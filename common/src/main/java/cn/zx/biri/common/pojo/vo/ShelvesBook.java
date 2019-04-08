package cn.zx.biri.common.pojo.vo;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 23:04 2019/4/8 0008
 */
public class ShelvesBook extends BookWithBLOBs {
    private List<Integer> tagId;
    private Integer authorId;

    public List<Integer> getTagId() {
        return tagId;
    }

    public void setTagId(List<Integer> tagId) {
        this.tagId = tagId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
