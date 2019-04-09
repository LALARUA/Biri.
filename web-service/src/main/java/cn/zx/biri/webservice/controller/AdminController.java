package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Tag;

import cn.zx.biri.common.pojo.response.NewTag;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.webservice.feignService.AdminService;
import cn.zx.biri.webservice.feignService.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private AdminService adminService;

    @Autowired
    private BookService bookService;

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
        Map map = adminService.shelvesBook();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String tagMap = objectMapper.writeValueAsString((Map) map.get("tagMap"));

        model.addAttribute("authors",map.get("authors"));
        model.addAttribute("tagHead",map.get("tagHead"));
        model.addAttribute("tagMap",map.get("tagMap"));
        return "admin/shelvesBook";
    }
    @GetMapping("manageTag")
    public String manageTag(Model model) throws Exception{
        Map map = adminService.manageTag();
        model.addAttribute("tagHead",map.get("tagHead"));
        model.addAttribute("tagMap",map.get("tagMap"));
        return "admin/manageTag";

    }
    @GetMapping("bookList")
    public String bookList(SelectBook condition,Model model) throws Exception{

        condition.setFlag("admin");
        condition.setPageNow(1);
        Map bookMap = bookService.bookList(condition);
        if (bookMap==null)
            return "bookList";
        List<Integer> bookIds = (List<Integer>) bookMap.get("bookIds");
        condition.setCurrentBookIds(bookIds);
        model.addAttribute("bookList",bookMap.get("bookList"));
        model.addAttribute("pageNum",(bookIds.size()-1)/pageSize+1);
        model.addAttribute("count",bookIds.size());
        return "admin/bookList";
    }
}
