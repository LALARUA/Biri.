package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.commonBean.RegEx;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.webservice.feignService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:33 2019/2/28 0028
 */

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("book/list")
    public String bookList(SelectBook condition, Model model) {
        condition.setPageNow(1);
        Map bookMap = bookService.bookList(condition);
        bookMap.get("bookList");
        model.addAttribute("bookList",bookMap.get("bookList"));
        model.addAttribute("pageNum",bookMap.get("pageNum"));
        model.addAttribute("count",bookMap.get("count"));
        model.addAttribute("condition",condition);
        return "bookList";
    }
    @GetMapping("book/listByPage")
    public String bookListByPage(SelectBook condition, Model model) {
        model.addAttribute("bookList",bookService.bookListByPage(condition));
        return "bookList::bookList";
    }

    @GetMapping("book/search")
    public String bookSearch(String keyword,Model model){
        SelectBook condition = new SelectBook();
        if (keyword.matches(RegEx.ISBN.toString())){
            condition.setIsbn(Long.valueOf(keyword));
        }
        else {
            keyword = "%"+keyword+"%";
            condition.setTagName(keyword);
            condition.setAuthorName(keyword);
            condition.setTitle(keyword);
        }
        return bookList(condition,model);
    }

    @GetMapping("book/detail/{bookId}")
    public String bookDetail(@PathVariable("bookId") Integer bookId,Model model){
        BookDetail bookDetail = bookService.selectBookDetail(bookId, 4);
        model.addAttribute("bookDetail",bookDetail);
        return "bookDetail";
    }



}
