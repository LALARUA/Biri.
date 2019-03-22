package cn.zx.biri.bookservice;

import cn.zx.biri.bookservice.mapper.BookMapper;
import cn.zx.biri.bookservice.mapper.BookWithAuthorMapper;
import cn.zx.biri.bookservice.mapper.BookWithImagePathMapper;
import cn.zx.biri.bookservice.service.BookService;
import cn.zx.biri.common.pojo.entry.BookWithImagePath;
import cn.zx.biri.common.pojo.entry.Tag;
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

		Tag tag1 = new Tag(2,3);
		Tag tag2 = new Tag(4,2);
		Tag tag3 = new Tag(5,1);
		Tag tag4 = new Tag(1,4);
		Tag tag5 = new Tag(3,0);

		List<Tag> tags = new LinkedList<>();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		tags.add(tag4);
		tags.add(tag5);

		Map<Integer,Tag> headMap = new HashMap<>();
		Map<Integer,Tag> tailMap = new HashMap<>();
		Deque<Tag> queue = new LinkedList();

		for (Tag t : tags) {

			if (queue.isEmpty()){
				queue.addLast(t);
				continue;
			}
			Tag tail = queue.peekLast();
			if (t.getfatherId()==tail.getId()) {
				queue.addLast(t);
				continue;
			}
			Tag head = queue.peekFirst();
			if (head.getfatherId()==t.getId()){
				queue.addFirst(t);
				continue;
			}

		}
		while (true){
			Tag head = queue.peekFirst();
			Tag newHead = headMap.get(head.getfatherId());
			if (newHead!=null){
				queue.addFirst(newHead);
			}
			else break;
		}
		while (true){
			Tag tail = queue.peekLast();
			Tag newTail = tailMap.get(tail.getId());
			if (newTail!=null){
				queue.addLast(newTail);
			}
			else break;
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
