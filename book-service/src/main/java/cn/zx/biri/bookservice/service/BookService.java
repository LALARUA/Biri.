package cn.zx.biri.bookservice.service;

import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.BookWithStatus;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.BookStatusEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.common.pojo.vo.ShelvesBook;
import org.springframework.web.multipart.MultipartFile;


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
    int reduceStock(Map<Integer,BookInCart> map);
    Map shelvesBook();
    void postShelvesBook(List<MultipartFile> bookImg,ShelvesBook book) throws Exception;
    Map manageTag();
    List<BookEnhanced> allBookList();
    BookDetail editBook(Integer bookId);
    void editStatus(BookStatusEnhanced BookStatusEnhanced);
    void updateBook(Book book);

}
