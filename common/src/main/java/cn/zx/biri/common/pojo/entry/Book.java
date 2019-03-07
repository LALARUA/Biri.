package cn.zx.biri.common.pojo.entry;

import java.util.Date;

public class Book {
    private Integer id;

    private String title;

    private String imagePath;

    private String publisher;

    private Double price;

    private Long isbn;

    private Date putaway;

    private Integer sales;

    private Integer stock;

    private Integer authorId;

    private String publishDate;

    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getimagePath() {
        return imagePath;
    }

    public void setimagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Date getPutaway() {
        return putaway;
    }

    public void setPutaway(Date putaway) {
        this.putaway = putaway;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getauthorId() {
        return authorId;
    }

    public void setauthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getpublishDate() {
        return publishDate;
    }

    public void setpublishDate(String publishDate) {
        this.publishDate = publishDate == null ? null : publishDate.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}