package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.Tag;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:27 2019/3/6 0006
 */
public class BookListDisplay extends Book {
    private Author author;
    private List<Tag> tags;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
