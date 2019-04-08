package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.WishList;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:09 2019/4/4 0004
 */
public class UserWishList extends WishList {

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
