package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.OrderDetail;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:16 2019/3/28 0028
 */
public class UserOrderDetail extends OrderDetail {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
