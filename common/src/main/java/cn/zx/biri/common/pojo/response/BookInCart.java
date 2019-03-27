package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Book;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:42 2019/3/24 0024
 */
public class BookInCart extends Book {

    private Integer bookNum;
    private Integer cartId;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }
}
