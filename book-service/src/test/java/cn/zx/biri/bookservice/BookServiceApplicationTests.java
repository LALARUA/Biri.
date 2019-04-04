package cn.zx.biri.bookservice;

import cn.zx.biri.bookservice.mapper.*;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.entry.*;
import cn.zx.biri.common.pojo.example.*;
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
	BookwithtagMapper bookwithtagMapper;

	@Autowired
	TagMapper tagMapper;

	@Autowired
	AuthorMapper authorMapper;

	@Autowired
	BookService bookService;


	@Test
	public void test(){
		SelectBook selectBook = new SelectBook();
		selectBook.setKeyword("余华");
		selectBook.setTagIds(new ArrayList<>(Arrays.asList(151,152)));
		selectBook.setCurrentBookIds(new ArrayList<>(Arrays.asList(787)));
		List<Integer> list = bookMapper.selectBookCount(selectBook);
		Integer integer = bookMapper.selectBookCountByCondition(selectBook);

		return;
	}


	@Test
	public void contextLoads() {
//		BookExample bookExample = new BookExample();
//		bookExample.createCriteria().andIdGreaterThanOrEqualTo(903);
//		List<BookWithBLOBs> books = bookMapper.selectByExampleWithBLOBs(bookExample);
//		for (BookWithBLOBs book  : books) {
//		StringBuilder keyword = new StringBuilder();
//		keyword.append(book.getTitle() + ",");
//		BookWithAuthorExample bookWithAuthorExample = new BookWithAuthorExample();
//		bookWithAuthorExample.createCriteria().andBookIdEqualTo(book.getId());
//		List<BookWithAuthor> bookWithAuthors = bookWithAuthorMapper.selectByExample(bookWithAuthorExample);
//		if (bookWithAuthors.size()>0) {
//			List<Integer> authorIds = new ArrayList<>();
//			for (BookWithAuthor b : bookWithAuthors) {
//				authorIds.add(b.getAuthorId());
//			}
//			AuthorExample authorExample = new AuthorExample();
//			authorExample.createCriteria().andIdIn(authorIds);
//			List<Author> authors = authorMapper.selectByExample(authorExample);
//			for (Author a : authors) {
//				keyword.append(a.getName() + ",");
//			}
//		}
//		keyword.append(book.getPublishers() + ",");
//		BookwithtagExample bookwithtagExample = new BookwithtagExample();
//		bookwithtagExample.createCriteria().andBookidEqualTo(book.getId());
//		List<Bookwithtag> bookwithtags = bookwithtagMapper.selectByExample(bookwithtagExample);
//		if (bookwithtags.size()>0) {
//			List<Integer> tagIds = new ArrayList<>();
//			for (Bookwithtag b : bookwithtags) {
//				tagIds.add(b.getTagid());
//			}
//			TagExample tagExample = new TagExample();
//			TagExample.Criteria criteria = tagExample.createCriteria().andIdIn(tagIds);
//			List<Tag> tags = tagMapper.selectByExample(tagExample);
//			for (Tag t : tags) {
//				keyword.append(t.getName() + ",");
//			}
//		}
//		bookMapper.setKeyWord(keyword.toString(), book.getId());
//	}

		}
}





