package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Tag;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:02 2019/3/31 0031
 */
public class FilterBookCondition {
    private List<Tag> tags;
    private List<Author> authors;
    private List<String> publishers;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }
}
