package cn.zx.biri.bookservice.controller;

import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.vo.SelectBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public Map bookList(@RequestBody SelectBook condition, HttpServletRequest httpServletRequest){
        return bookService.selectBookList(condition);
    }

//    @Cacheable(value = "bookList",key = "#condition")
    @GetMapping("bookListByPage")
    public List<BookEnhanced> bookListByPage(@RequestBody SelectBook condition){
        return bookService.selectBookListByPage(condition);
    }

    @GetMapping("reduceStock")
    public int reduceStock(HttpSession httpSession){
       Map<Integer,BookInCart> map = (Map<Integer, BookInCart>) httpSession.getAttribute("cart");
        try {
            return bookService.reduceStock(map);
        } catch (Exception e) {
            return -1;
        }
    }





}
