package cn.zx.biri.webservice.controller;

import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.webservice.feignService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:02 2019/3/23 0023
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping("comment")
    public String commentListByPage(Integer bookId, Integer pageNow, Model model){
        List<BookComment> bookComments = commentService.selectBookCommentsByPage(bookId, 4, pageNow);
        model.addAttribute("comments",bookComments);
        return "bookDetail::bookComments";
    }


}
