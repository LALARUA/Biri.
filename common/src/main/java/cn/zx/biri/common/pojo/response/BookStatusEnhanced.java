package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.BookStatus;
import cn.zx.biri.common.pojo.entry.BookWithStatus;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 21:24 2019/4/9 0009
 */
public class BookStatusEnhanced extends BookWithStatus {
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
