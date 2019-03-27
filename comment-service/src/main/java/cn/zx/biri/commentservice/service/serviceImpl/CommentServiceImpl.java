package cn.zx.biri.commentservice.service.serviceImpl;

import cn.zx.biri.commentservice.mapper.CommentMapper;
import cn.zx.biri.commentservice.service.CommentService;
import cn.zx.biri.common.pojo.entry.Comment;
import cn.zx.biri.common.pojo.example.CommentExample;
import cn.zx.biri.common.pojo.response.BookComment;
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
public class CommentServiceImpl implements CommentService {
    private final int pageSize = 1;
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplyServiceImpl replyService;

    /**
     * @description  图书的评论（首页）
     * @author xiangXX
     * @date 2019/3/8 0008 8:48
      * @param bookId           图书id
     *  @param currentUserId    当前浏览用户id
     */
    @Override
    public Map selectBookCommentsFirst(Integer bookId,Integer currentUserId){
        Map map = new HashMap();
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andBookIdEqualTo(bookId);
        long commentCount = commentMapper.countByExample(commentExample);
        int pageNum = (int) (commentCount-1)/pageSize+1;
        map.put("commentCount",commentCount);
        map.put("pageNum",pageNum);
        map.put("bookComments",selectBookCommentsByPage(bookId,currentUserId,1));
        return map;
    }

    /**
     * @description 分页查询图书评论
     * @author xiangXX
     * @date 2019/3/11 0011 13:56
      * @param pageNow 查询的页数
     *
     */
    @Override
    public List<BookComment> selectBookCommentsByPage(Integer bookId, Integer currentUserId, Integer pageNow) {
        Map map = new HashMap();
        map.put("bookId",bookId);
        map.put("currentUserId",currentUserId);
        map.put("start",(pageNow-1)*pageSize);
        List<BookComment> bookComments = commentMapper.selectBookComments(map);
        if (bookComments.size()==0)
            return null;
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
        return bookComments;
    }

    @Override
    public void submitComment(Comment comment) throws Exception {
        commentMapper.insertSelective(comment);
    }
}
