package cn.zx.biri.common.pojo.entry;

public class OrderDetail {
    private Integer id;

    private Integer orderId;

    private Integer bookId;

    private Integer bookNum;

    private Double summoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Double getSummoney() {
        return summoney;
    }

    public void setSummoney(Double summoney) {
        this.summoney = summoney;
    }
}