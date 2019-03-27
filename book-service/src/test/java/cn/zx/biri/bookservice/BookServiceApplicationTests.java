package cn.zx.biri.bookservice;

import cn.zx.biri.bookservice.mapper.BookMapper;
import cn.zx.biri.bookservice.mapper.BookWithAuthorMapper;
import cn.zx.biri.bookservice.mapper.BookWithImagePathMapper;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.entry.Book;
import cn.zx.biri.common.pojo.entry.BookWithBLOBs;
import cn.zx.biri.common.pojo.entry.BookWithImagePath;
import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.example.BookExample;
import cn.zx.biri.common.pojo.example.BookWithImagePathExample;
import cn.zx.biri.common.pojo.response.BookDetail;
import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.common.pojo.vo.SelectBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceApplicationTests {

	@Autowired
	BookMapper bookMapper;

	@Autowired
	BookWithAuthorMapper bookWithAuthorMapper;

	@Autowired
	BookWithImagePathMapper bookWithImagePathMapper;

	@Autowired
	BookService bookService;



	@Test
	public void contextLoads() {

		List<BookWithBLOBs> books = bookMapper.selectByExampleWithBLOBs(new BookExample());
		for (BookWithBLOBs book  : books) {
			BookWithImagePathExample bookWithImagePathExample = new BookWithImagePathExample();
			bookWithImagePathExample.createCriteria().andBookIdEqualTo(book.getId());
			List<BookWithImagePath> bookWithImagePaths = bookWithImagePathMapper.selectByExample(bookWithImagePathExample);
			if(bookWithImagePaths.size()!=0){
				String bookImagePath = bookWithImagePaths.get(0).getBookImagePath();
				book.setimagePath(bookImagePath);

				BookExample bookExample = new BookExample();
				bookExample.createCriteria().andIdEqualTo(book.getId());
				bookMapper.updateByExampleSelective(book,bookExample);
			}


		}
		return;
//		File file = new File("E:\\gitRep\\BiriPic\\bookPic");
//		File[] files = file.listFiles();
//		for (File f : files) {
//			File[] imageFile = f.listFiles();
//			String name1 = f.getName();
//			for (File image : imageFile) {
//				String name = image.getName();
//				String bookImagePath = name1+"/"+name;
//				BookWithImagePath bookWithImagePath = new BookWithImagePath();
//				bookWithImagePath.setBookId(Integer.valueOf(name1));
//				bookWithImagePath.setBookImagePath(bookImagePath);
//				bookWithImagePath.setStatus(0);
//				bookWithImagePathMapper.insertSelective(bookWithImagePath);
//			}
//
//		}
	}




}
