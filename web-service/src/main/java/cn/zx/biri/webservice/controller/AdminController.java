package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.Tag;

import cn.zx.biri.common.pojo.response.NewTag;
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
    @Autowired
    private AdminService adminService;

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
}
