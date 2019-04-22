package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.mapper.AuthorMapper;
import cn.zx.biri.bookservice.service.AuthorService;
import cn.zx.biri.common.pojo.entry.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:55 2019/4/8 0008
 */
@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<Author> getAllAuthors() {

        List<Author> authors = authorMapper.getAllAuthors();
        return authors;
    }
}
