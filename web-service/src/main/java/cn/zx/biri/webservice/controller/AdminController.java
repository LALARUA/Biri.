package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.Tag;

import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.response.NewTag;
import cn.zx.biri.common.pojo.vo.SelectBook;

import cn.zx.biri.webservice.feignService.BookService;
import cn.zx.biri.webservice.service.BBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:38 2019/4/7 0007
 */
@RequestMapping("admin")
@Controller
public class AdminController {
    private final Integer pageSize = 12;

    @Autowired
    BookService bookService;

    @Autowired
    BBookService bBookService;



    @GetMapping("index")
    public String adminIndex(){

        return "admin/index";
    }

    @GetMapping("handleTag")
    public String handleTag(){

        return "admin/handleTag";
    }

    @GetMapping("shelvesBook")
    public String shelvesBook(Model model) throws Exception{
        Map map = bookService.shelvesBook();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String tagMap = objectMapper.writeValueAsString((Map) map.get("tagMap"));

        model.addAttribute("authors",map.get("authors"));
        model.addAttribute("tagHead",map.get("tagHead"));
        model.addAttribute("tagMap",map.get("tagMap"));
        return "admin/shelvesBook";
    }
    @GetMapping("manageTag")
    public String manageTag(Model model) throws Exception{
        Map map = bookService.manageTag();
        model.addAttribute("tagHead",map.get("tagHead"));
        model.addAttribute("tagMap",map.get("tagMap"));
        return "admin/manageTag";

    }
    @GetMapping("bookList")
    public String bookList(Model model) throws Exception{
        List<BookEnhanced> bookList = bBookService.allBookList("allBookList");
        model.addAttribute("bookList",bookList);
        return "admin/bookList";
    }
    @GetMapping("editBook/{bookId}")
    public String editBook(Model model, @PathVariable("bookId") Integer bookId) throws Exception{
        BookDetail bookDetail = bookService.editBook(bookId);
        Map map = bookService.shelvesBook();
        model.addAttribute("authors",map.get("authors"));
        model.addAttribute("tagHead",map.get("tagHead"));
        model.addAttribute("tagMap",map.get("tagMap"));
        model.addAttribute("bookDetail",bookDetail);
        return "admin/editBook";
    }
    @GetMapping("manageAuthor")
    public String manageAuthor(Model model) throws Exception{


        return "admin/manageAuthor";
    }
    @GetMapping("manageOrder")
    public String manageOrder(Model model) throws Exception{



        return "admin/manageOrder";
    }


}
