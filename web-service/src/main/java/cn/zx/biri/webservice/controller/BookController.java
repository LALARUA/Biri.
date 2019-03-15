package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.webservice.feignService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:33 2019/2/28 0028
 */
@RequestMapping("book")
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("list")
    public String bookList(SelectBook condition) {
        bookService.bookList(condition);

        return "bookList";
    }

}
