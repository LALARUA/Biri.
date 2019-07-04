package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.config.MyProperties;
import cn.zx.biri.bookservice.feignService.CommentService;
import cn.zx.biri.bookservice.mapper.*;
import cn.zx.biri.bookservice.service.AuthorService;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.bookservice.service.TagService;
import cn.zx.biri.common.pojo.entry.*;
import cn.zx.biri.common.pojo.response.*;
import cn.zx.biri.common.pojo.vo.SelectBook;
import cn.zx.biri.common.pojo.vo.ShelvesBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private BookMapper bookMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MyProperties myProperties;
    @Autowired
    private BookWithAuthorMapper bookWithAuthorMapper;
    @Autowired
    private BookWithImagePathMapper bookWithImagePathMapper;
    @Autowired
    private BookwithtagMapper bookwithtagMapper;
    @Autowired
    private BookWithStatusMapper bookWithStatusMapper;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Map selectBookList(SelectBook condition) {
        Map map = new HashMap();
        List<Integer> bookIds = bookMapper.selectBookCount(condition);
        if (bookIds.size()==0)
            return null;
        if (condition.getCurrentBookIds()==null&&condition.getFlag()==null){
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
        String[] catalogs = catalog.split(" |\r\n");
        bookDetail.setCatalogs(new ArrayList<String>(Arrays.asList(catalogs)));
        bookDetail.setTagLinks(tagService.tagLink(bookDetail.getTags()));
        bookDetail.setCommentCount((Integer) map.get("commentCount"));
        bookDetail.setCommentPageNum((Integer) map.get("pageNum"));
        List bookCommentsMap = (List) map.get("bookComments");
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

    @Override
    public Map shelvesBook() {
        Map map = new HashMap();

        Map head = tagService.getHead("tags");
        List<Author> allAuthors = authorService.getAllAuthors();

        map.put("tagHead",head.get("tagHead"));
        map.put("authors",allAuthors);
        map.put("tagMap",head.get("tagMap"));

        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void postShelvesBook(List<MultipartFile> bookImg,ShelvesBook book) throws Exception {
        String bookImgPathPrefix = new String();
        String system = System.getProperty("os.name").toLowerCase();
        if (system.contains("win")){
            bookImgPathPrefix = myProperties.getWindowBookImagePathPrefix();
        }else if (system.contains("linux")){
            bookImgPathPrefix = myProperties.getLinuxBookImagePathPrefix();
        }
        StringBuilder keyword = new StringBuilder();
        String authorIdAndName = book.getAuthorIdAndName();
        String[] authorIdAndNameSplit = authorIdAndName.split("-");
        Integer authorId = Integer.valueOf(authorIdAndNameSplit[0]);
        String authorName = authorIdAndNameSplit[1];
        keyword.append(book.getTitle()+",");
        keyword.append(authorName+",");
        for (String tagIdAndName : book.getTagIdAndName()) {
            if (tagIdAndName.equals(""))
                continue;
            String[] tagIdAndNameSplit = tagIdAndName.split("-");
            book.getTagId().add(Integer.valueOf(tagIdAndNameSplit[0]));
            keyword.append(tagIdAndNameSplit[1]+",");
        }
        book.setKeyword(keyword.toString());
        bookMapper.insertSelective(book);
        for (Integer tagId : book.getTagId()) {
            Bookwithtag bookwithtag = new Bookwithtag();
            bookwithtag.setBookid(book.getId());
            bookwithtag.setTagid(tagId);
            bookwithtagMapper.insertSelective(bookwithtag);
        }
        BookWithAuthor bookWithAuthor = new BookWithAuthor();
        bookWithAuthor.setAuthorId(authorId);
        bookWithAuthor.setBookId(book.getId());
        bookWithAuthorMapper.insertSelective(bookWithAuthor);
        File file = new File(bookImgPathPrefix+book.getId());
        file.mkdir();
        for (int i = 0; i < bookImg.size(); i++) {
            MultipartFile img = bookImg.get(i);
            String originalFilename = img.getOriginalFilename();
            String newImgName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
            String imagePath = book.getId()+"/"+newImgName;
            if (i==0){
                book.setImagePath(imagePath);
                bookMapper.updateByPrimaryKeySelective(book);
            }
            BookWithImagePath bookWithImagePath = new BookWithImagePath();
            bookWithImagePath.setBookImagePath(imagePath);
            bookWithImagePath.setBookId(book.getId());
            bookWithImagePathMapper.insertSelective(bookWithImagePath);
            File imgFile = new File(bookImgPathPrefix+"/"+imagePath);
            img.transferTo(imgFile);
        }

        Map<Integer,BookEnhanced> bookEnhancedMap = (Map<Integer, BookEnhanced>) redisTemplate.opsForValue().get("importantCache::allBookList");
        BookEnhanced bookEnhanced = selectBookDetail(book.getId(),4);
        bookEnhancedMap.put(bookEnhanced.getId(),bookEnhanced);
        redisTemplate.opsForValue().set("importantCache::allBookList",bookEnhancedMap);

    }

    @Override
    public Map manageTag() {
        return tagService.getHead("tags");

    }

    @Override
    public List<BookEnhanced> allBookList() {
        return bookMapper.selectAllBookList();
    }

    @Override
    public BookDetail editBook(Integer bookId) {
        BookDetail bookDetail = bookMapper.selectBookDetail(bookId);
        bookDetail.setTagLinks(tagService.tagLink(bookDetail.getTags()));
        return bookDetail;
    }

    @Override
    public void editStatus(BookStatusEnhanced bookStatusEnhanced) {
        Map<Integer,BookEnhanced> bookEnhancedMap = (Map<Integer, BookEnhanced>) redisTemplate.opsForValue().get("importantCache::allBookList");

        if (bookStatusEnhanced.getId()==null){
            bookWithStatusMapper.insertSelective(bookStatusEnhanced);
            BookEnhanced bookEnhanced = bookEnhancedMap.get(bookStatusEnhanced.getId());
            bookEnhanced.getStatus().add(bookStatusEnhanced);
        }

        else {
            bookWithStatusMapper.deleteByPrimaryKey(bookStatusEnhanced.getId());
            BookEnhanced bookEnhanced = bookEnhancedMap.get(bookStatusEnhanced.getId());
            bookEnhanced.getStatus().remove(0);
        }

        redisTemplate.opsForValue().set("importantCache::allBookList",bookEnhancedMap);


    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateByPrimaryKey(book);
    }


}
