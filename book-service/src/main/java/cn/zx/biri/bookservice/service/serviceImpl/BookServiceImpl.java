package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.config.MyProperties;
import cn.zx.biri.bookservice.feignService.CommentService;
import cn.zx.biri.bookservice.mapper.BookMapper;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.common.utils.HandleBookTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:54 2019/3/7 0007
 */
@EnableConfigurationProperties(MyProperties.class)
@Service
public class BookServiceImpl implements BookService{
    private final int pageSize = 12;

    @Autowired
    BookMapper bookMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    MyProperties myProperties;

    @Override
    public Map selectBookList(SelectBook condition) {
        Map map = new HashMap();
        Integer bookCount = bookMapper.selectBookCountByCondition(condition);
        if (bookCount==0)
            return null;
        Integer pageNum = (bookCount-1)/pageSize+1;
        map.put("pageNum",pageNum);
        map.put("bookList",selectBookListByPage(condition));
        map.put("count",bookCount);
        return map;
    }

    @Override
    public List<BookEnhanced> selectBookListByPage(SelectBook condition) {
        Integer pageNow = condition.getPageNow();
        condition.setStart((pageNow-1)*pageSize);
        List<BookEnhanced> bookList = bookMapper.selectBookList(condition);

        condition.setStart(null);
        return bookList;
    }


    @Override
    public BookDetail selectBookDetail(Integer bookId,Integer currentUserId) {
        BookDetail bookDetail = bookMapper.selectBookDetail(bookId);
        if (bookDetail==null)
            return null;

        Map map = commentService.selectBookCommentsFirst(bookId, currentUserId);
        String catalog = bookDetail.getCatalog();
        String[] catalogs = catalog.split(" ");
        bookDetail.setCatalogs(new ArrayList<String>(Arrays.asList(catalogs)));
        bookDetail.setTagLinks(HandleBookTagUtils.tagLink(bookDetail.getTags()));
        bookDetail.setCommentCount((Integer) map.get("commentCount"));
        bookDetail.setCommentPageNum((Integer) map.get("pageNum"));
        List bookCommentsMap = (List) map.get("bookComments");
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<BookComment> bookComments = new ArrayList<>(bookCommentsMap.size());
//        for (Map bookComment : bookCommentsMap) {
//            bookComments.add(objectMapper.convertValue(bookComment, BookComment.class));
//        }
        bookDetail.setComments(bookCommentsMap);
        return bookDetail;
    }


}
