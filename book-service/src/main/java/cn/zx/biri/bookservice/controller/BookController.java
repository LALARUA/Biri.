package cn.zx.biri.bookservice.controller;

import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 11:07 2019/3/6 0006
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("bookDetail")
    public BookDetail selectBookDetail(Integer bookId, Integer currentUserId){
       return bookService.selectBookDetail(bookId,currentUserId);
    }

//    @Cacheable(value = "bookList",key = "#condition")
    @GetMapping("bookList")
    public Map bookList(@RequestBody SelectBook condition){
       return bookService.selectBookList(condition);
    }

//    @Cacheable(value = "bookList",key = "#condition")
    @GetMapping("bookListByPage")
    public List<BookEnhanced> bookListByPage(@RequestBody SelectBook condition){
        return bookService.selectBookListByPage(condition);
    }

}
