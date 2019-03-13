package cn.zx.biri.bookservice.feignService;

import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:30 2019/3/7 0007
 */
@FeignClient(value = "comment-service")
public interface CommentService {

    @GetMapping("selectBookCommentsFirst")
    Map selectBookCommentsFirst(@RequestParam("bookId") Integer bookId,@RequestParam("currentUserId") Integer currentUserId);

    @GetMapping("selectBookCommentsByPage")
    List<BookComment> selectBookCommentsByPage(@RequestParam("bookId") Integer bookId,@RequestParam("currentUserId") Integer currentUserId, @RequestParam("pageNow") Integer pageNow);

    @GetMapping("getReplyByCommentIdAndPageNow")
    List<ReplyEnhanced> getReplyByCommentIdAndPageNow(@RequestParam("bookId") Integer commentId, @RequestParam("currentUserId") Integer currentUserId, @RequestParam("pageNow") Integer pageNow);

}
