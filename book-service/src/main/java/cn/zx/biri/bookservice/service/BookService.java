package cn.zx.biri.bookservice.service;

import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:53 2019/3/7 0007
 */

public interface BookService {
    Map selectBookList(SelectBook condition);
    List<BookEnhanced> selectBookListByPage(SelectBook condition);
    BookDetail selectBookDetail(Integer bookId,Integer currentUserId);
}
