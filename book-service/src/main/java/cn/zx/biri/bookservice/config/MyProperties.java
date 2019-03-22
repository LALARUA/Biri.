package cn.zx.biri.bookservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:09 2019/3/17 0017
 */
@ConfigurationProperties(prefix = "book.properties")
public class MyProperties {
    private String bookImagePathPrefix;

    public String getBookImagePathPrefix() {
        return bookImagePathPrefix;
    }

    public void setBookImagePathPrefix(String bookImagePathPrefix) {
        this.bookImagePathPrefix = bookImagePathPrefix;
    }
}
