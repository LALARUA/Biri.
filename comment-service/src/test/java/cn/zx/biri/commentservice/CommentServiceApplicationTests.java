package cn.zx.biri.commentservice;

import cn.zx.biri.commentservice.mapper.CommentMapper;
import cn.zx.biri.commentservice.mapper.ReplyMapper;
import cn.zx.biri.commentservice.service.CommentService;
import cn.zx.biri.commentservice.service.serviceImpl.CommentServiceImpl;
import cn.zx.biri.common.pojo.response.BookComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceApplicationTests {
	@Autowired
	CommentMapper commentMapper;

	@Autowired
	ReplyMapper replyMapper;

	@Autowired
	CommentService commentService;
	@Test
	public void contextLoads() {



		Map map = new HashMap<>();
		map.put("currentUserId",4);
		map.put("bookId",888);
		List<BookComment> bookComments = commentMapper.selectBookComments(map);
		System.out.println(bookComments);
		return;
	}

}
