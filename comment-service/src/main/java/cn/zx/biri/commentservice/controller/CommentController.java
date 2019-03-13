package cn.zx.biri.commentservice.controller;

import cn.zx.biri.commentservice.service.CommentService;
import cn.zx.biri.common.pojo.response.BookComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:46 2019/3/7 0007
 */
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("selectBookCommentsFirst")
    public Map selectBookCommentsFirst(Integer bookId, Integer currentUserId) {
        return commentService.selectBookCommentsFirst(bookId, currentUserId);
    }

    @GetMapping("selectBookCommentsByPage")
    List<BookComment> selectBookCommentsByPage(Integer bookId, Integer currentUserId, Integer pageNow) {
        return commentService.selectBookCommentsByPage(bookId,currentUserId,pageNow);
    }
}