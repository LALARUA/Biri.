package cn.zx.biri.webservice.service.serviceImpl;

import cn.zx.biri.common.pojo.response.BookEnhanced;
import cn.zx.biri.webservice.feignService.BookService;
import cn.zx.biri.webservice.service.BBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:46 2019/3/30 0030
 */
@Service
public class BBookServiceImpl implements BBookService {
    @Autowired
    private BookService bookService;


    @Override
    @Cacheable(cacheNames = "importantCache",key = "#key")
    public String tagsHTML(String key) {
       return bookService.tagsHTML();
    }

    @Override
    @Cacheable(cacheNames = "importantCache",key = "#key")
    public List<BookEnhanced> allBookList(String key) {
       return bookService.allBookList();

    }


}
