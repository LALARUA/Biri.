package cn.zx.biri.common.pojo.entry;

import java.io.Serializable;

public class BookWithImagePath implements Serializable {
    private Integer id;

    private Integer bookId;

    private String bookImagePath;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookImagePath() {
        return bookImagePath;
    }

    public void setBookImagePath(String bookImagePath) {
        this.bookImagePath = bookImagePath == null ? null : bookImagePath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}