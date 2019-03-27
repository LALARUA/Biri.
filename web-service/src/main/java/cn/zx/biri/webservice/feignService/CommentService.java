package cn.zx.biri.webservice.feignService;

import cn.zx.biri.common.pojo.response.BookComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:56 2019/3/23 0023
 */
@FeignClient(value = "comment-service")
public interface CommentService {

    @GetMapping("comment")
    List<BookComment> selectBookCommentsByPage(@RequestParam("bookId") Integer bookId, @RequestParam("currentUserId") Integer currentUserId, @RequestParam("pageNow") Integer pageNow);
}
