package cn.zx.biri.bookservice.service;

import cn.zx.biri.common.pojo.entry.Author;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:54 2019/4/8 0008
 */
public interface AuthorService {

    List<Author> getAllAuthors();
    Author authorDetail(Integer authorId);
    void updateAuthor(Author author);
    void insertAuthor(Author author);

}
