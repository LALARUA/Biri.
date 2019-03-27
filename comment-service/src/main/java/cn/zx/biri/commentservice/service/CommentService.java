package cn.zx.biri.commentservice.service;

import cn.zx.biri.common.pojo.entry.Comment;
import cn.zx.biri.common.pojo.response.BookComment;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:08 2019/3/7 0007
 */
public interface CommentService {
    Map selectBookCommentsFirst(Integer bookId, Integer currentUserId);
    List<BookComment> selectBookCommentsByPage(Integer bookId,Integer currentUserId,Integer pageNow);
    void submitComment(Comment comment) throws Exception;
}
