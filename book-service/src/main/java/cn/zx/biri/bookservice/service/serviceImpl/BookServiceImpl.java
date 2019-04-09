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
    BookMapper bookMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    MyProperties myProperties;
    @Autowired
    BookWithAuthorMapper bookWithAuthorMapper;
    @Autowired
    BookWithImagePathMapper bookWithImagePathMapper;
    @Autowired
    BookwithtagMapper bookwithtagMapper;

    @Autowired
    AuthorService authorService;

    @Autowired
    TagService tagService;


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
        String[] catalogs = catalog.split(" |\r\n");
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
    }

    @Override
    public Map manageTag() {
        return tagService.getHead("tags");

    }


}
