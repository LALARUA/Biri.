package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.feignService.CommentService;
import cn.zx.biri.bookservice.mapper.BookMapper;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.response.BookComment;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:54 2019/3/7 0007
 */
@Service
public class BookServiceImpl implements BookService{
    private final int pageSize = 12;

    @Autowired
    BookMapper bookMapper;
    @Autowired
    CommentService commentService;

    @Override
    public Map selectBookList(SelectBook condition) {
        Map map = new HashMap();
        Integer bookCount = bookMapper.selectBookCountByCondition(condition);
        if (bookCount==0)
            return null;
        Integer pageNum = (bookCount-1)/pageSize+1;
        condition.setPageNow(1);
        map.put("pageNum",pageNum);
        map.put("bookList",selectBookListByPage(condition));
        return map;
    }

    @Override
    public List<BookEnhanced> selectBookListByPage(SelectBook condition) {
        Integer pageNow = condition.getPageNow();
        condition.setStart((pageNow-1)*pageSize);
        List<BookEnhanced> bookList = bookMapper.selectBookList(condition);
        return bookList;
    }


    @Override
    public BookDetail selectBookDetail(Integer bookId,Integer currentUserId) {
        BookDetail bookDetail = bookMapper.selectBookDetail(bookId);
        Map map = commentService.selectBookCommentsFirst(bookId, currentUserId);
        bookDetail.setCommentPageNum((Integer) map.get("pageNum"));
        List<Map> bookCommentsMap = (List) map.get("bookComments");
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookComment> bookComments = new ArrayList<>(bookCommentsMap.size());
        for (Map bookComment : bookCommentsMap) {
            bookComments.add(objectMapper.convertValue(bookComment, BookComment.class));
        }
        bookDetail.setComments(bookComments);
        return bookDetail;
    }

}
