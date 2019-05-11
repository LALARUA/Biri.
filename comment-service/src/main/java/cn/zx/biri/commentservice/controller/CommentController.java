package cn.zx.biri.commentservice.controller;

import cn.zx.biri.commentservice.service.CommentService;
import cn.zx.biri.common.pojo.entry.Comment;
import cn.zx.biri.common.pojo.entry.CommentSupport;
import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @GetMapping("comment")
    public List<BookComment> selectBookCommentsByPage(Integer bookId, Integer currentUserId, Integer pageNow) {
        return commentService.selectBookCommentsByPage(bookId,currentUserId,pageNow);
    }

    @PostMapping("comment")
    public String submitComment(@RequestBody Comment comment) throws Exception{

        try {

            String date = DateUtils.dateToString(new Date());
            comment.setDate(date);
            commentService.submitComment(comment);
        } catch (Exception e) {

            return "提交错误";
        }
        return "评论成功";

    }

    @PostMapping("commentSupport")
    public String commentSupport(CommentSupport commentSupport){
        try {
            commentService.commentSupport(commentSupport);
            return "suucess";
        } catch (Exception e) {
            return "error";
        }
    }
}