package cn.zx.biri.common.pojo.vo;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 23:04 2019/4/8 0008
 */
public class ShelvesBook extends BookWithBLOBs {
    private Set<Integer> tagId = new HashSet<>();
    private String authorIdAndName;
    private List<String> tagIdAndName;

    public List<String> getTagIdAndName() {
        return tagIdAndName;
    }

    public void setTagIdAndName(List<String> tagIdAndName) {
        this.tagIdAndName = tagIdAndName;
    }

    public Set<Integer> getTagId() {
        return tagId;
    }

    public void setTagId(Set<Integer> tagId) {
        this.tagId = tagId;
    }

    public String getAuthorIdAndName() {
        return authorIdAndName;
    }

    public void setAuthorIdAndName(String authorIdAndName) {
        this.authorIdAndName = authorIdAndName;
    }
}
