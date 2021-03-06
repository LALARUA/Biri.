package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.*;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:27 2019/3/6 0006
 */
public class BookEnhanced extends BookWithBLOBs {
    private List<Author> authors;
    private List<Tag> tags;
    private List<BookWithImagePath> imagePaths;
    private String hotComment;
    private List<BookStatusEnhanced> status;

    public List<BookStatusEnhanced> getStatus() {
        return status;
    }

    public void setStatus(List<BookStatusEnhanced> status) {
        this.status = status;
    }

    public String getHotComment() {
        return hotComment;
    }

    public void setHotComment(String hotComment) {
        this.hotComment = hotComment;
    }

    public List<BookWithImagePath> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<BookWithImagePath> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
