package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.config.MyProperties;
import cn.zx.biri.bookservice.feignService.CommentService;
import cn.zx.biri.bookservice.mapper.BookMapper;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.bookservice.service.TagService;
import cn.zx.biri.common.pojo.entry.Author;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;
import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.response.FilterBookCondition;
import cn.zx.biri.common.pojo.vo.SelectBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Autowired
    TagService tagService;


    @Override
    public Map selectBookList(SelectBook condition) {
        Map map = new HashMap();
        List<Integer> bookIds = bookMapper.selectBookCount(condition);
        if (bookIds.size()==0)
            return null;

        if (condition.getCurrentBookIds()==null){
            FilterBookCondition filter = new FilterBookCondition();
            List<Tag> filterTag = bookMapper.filterTag(bookIds);
            if (condition.getAuthorId() == null){
                List<Author> filterAuthor = bookMapper.filterAuthor(bookIds);
                filter.setAuthors(filterAuthor);
            }
            if (condition.getPublisher() == null){
                List<String> filterPublisher = bookMapper.filterPublisher(bookIds);
                filter.setPublishers(filterPublisher);
            }
            filter.setTags(filterTag);
            condition.setCurrentBookIds(bookIds);
            map.put("filter",filter);
        }
//        String tagHTML = tagService.getAllTagHTML();

//        BookDetail bookDetail = bookMapper.selectBookDetail(787);
//        List<Tag> tags = bookDetail.getTags();
//        StringBuilder stringBuilder = new StringBuilder();
//        Tag head = HandleBookTagUtils.getHead(tags);
//        HandleBookTagUtils.getTagHTML(head,stringBuilder);
//        String tagHTML= stringBuilder.toString();
        map.put("bookIds",bookIds);
        map.put("bookList",selectBookListByPage(condition));


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
        bookDetail.setTagLinks(tagService.tagLink(bookDetail.getTags()));
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


    @Transactional
    @Override
    public int reduceStock(Map<Integer, BookInCart> map) {
        for (Map.Entry<Integer,BookInCart> entry : map.entrySet()) {
            BookWithBLOBs book = bookMapper.getBookForUpdate(entry.getValue().getId());
            Integer stock = book.getStock();
            if (stock<entry.getValue().getBookNum()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
            else {
                book.setSales(book.getSales()+entry.getValue().getBookNum());
                book.setStock(book.getStock()-entry.getValue().getBookNum());
                bookMapper.updateByPrimaryKeySelective(book);
            }
        }

        return 0;
    }




}
