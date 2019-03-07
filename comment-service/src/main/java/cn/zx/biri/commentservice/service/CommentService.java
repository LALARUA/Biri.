package cn.zx.biri.commentservice.service;

import cn.zx.biri.commentservice.mapper.CommentMapper;
import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.common.pojo.response.ReplyEnhanced;
import cn.zx.biri.common.pojo.response.ReplyEnhancedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 11:26 2019/3/7 0007
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplyService replyService;


    public List<BookComment> selectBookComments(Integer bookId,Integer currentUserId){
        Map map = new HashMap();
        map.put("bookId",bookId);
        map.put("currentUserId",currentUserId);
        List<BookComment> bookComments = commentMapper.selectBookComments(map);
        Map<Integer,BookComment> idAndCommentMap = new HashMap<>();
        List<Integer> commentIdList = new ArrayList<>(bookComments.size());
        for (BookComment bookComment : bookComments) {
            Integer id = bookComment.getId();
            idAndCommentMap.put(id,bookComment);
            commentIdList.add(id);
        }

        List<ReplyEnhancedList> replies= replyService.getRepliesByCommentId(commentIdList,currentUserId);
        for (ReplyEnhancedList reply : replies) {
            Integer commentId = reply.getCommentId();
            BookComment bookComment = idAndCommentMap.get(commentId);
            bookComment.setReplyEnhancedList(reply);
        }
        return null;
    }
}
