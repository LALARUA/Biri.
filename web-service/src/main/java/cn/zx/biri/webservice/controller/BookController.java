package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.commonBean.RegEx;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.common.pojo.vo.SelectBookCondition;
import cn.zx.biri.webservice.feignService.BookService;
import cn.zx.biri.webservice.service.BBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:33 2019/2/28 0028
 */

@Controller
public class BookController {
    private final Integer pageSize = 12;

    @Autowired
    private BookService bookService;

    @Autowired
    private BBookService bBookService;

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("test")
    public void test(SelectBookCondition condition){

        return;
    }

    @GetMapping("book/list")
    public String bookList(SelectBook condition, Model model) {
        if (condition.getKeyword()!=null&&condition.getKeyword().matches(RegEx.ISBN.toString())){
            condition.setIsbn(Long.valueOf(condition.getKeyword()));
            condition.setKeyword(null);
        }
        condition.setPageNow(1);
        model.addAttribute("condition",condition);
        model.addAttribute("tagsHTML",bBookService.tagsHTML("allTags"));
        Map bookMap = bookService.bookList(condition);
        if (bookMap==null)
            return "bookList";
        List<Integer> bookIds = (List<Integer>) bookMap.get("bookIds");
        condition.setCurrentBookIds(bookIds);
        model.addAttribute("bookList",bookMap.get("bookList"));
        model.addAttribute("pageNum",(bookIds.size()-1)/pageSize+1);
        model.addAttribute("count",bookIds.size());
        model.addAttribute("filter",bookMap.get("filter"));
        return "bookList";
    }
    @GetMapping("book/listByPage")
    public String bookListByPage(SelectBook condition, Model model) {
        model.addAttribute("bookList",bookService.bookListByPage(condition));
        return "bookList::bookList";
    }
    @GetMapping("book/listByFilter")
    public String bookListByFilter(SelectBook condition, Model model) {
        condition.setPageNow(1);
        Map bookMap = bookService.bookList(condition);
        List<Integer> bookIds = (List<Integer>) bookMap.get("bookIds");
        model.addAttribute("bookList",bookMap.get("bookList"));
        model.addAttribute("pageNum",(bookIds.size()-1)/pageSize+1);
        model.addAttribute("count",bookIds.size());
        return "bookList::bookListAndPage";
    }

//    @GetMapping("book/search")
//    public String bookSearch(String keyword,Model model){
//        SelectBook condition = new SelectBook();
//        if (keyword.matches(RegEx.ISBN.toString())){
//            condition.setIsbn(Long.valueOf(keyword));
//        }
//        else {
//            keyword = "%"+keyword+"%";
//            condition.setTagName(keyword);
//            condition.setAuthorName(keyword);
//            condition.setTitle(keyword);
//        }
//        return bookList(condition,model);
//    }

    @GetMapping("book/detail/{bookId}")
    public String bookDetail(@PathVariable("bookId") Integer bookId,Model model){
        BookDetail bookDetail = bookService.selectBookDetail(bookId, 4);
        model.addAttribute("bookDetail",bookDetail);
        model.addAttribute("comments",bookDetail.getComments());
        return "bookDetail";
    }





}
