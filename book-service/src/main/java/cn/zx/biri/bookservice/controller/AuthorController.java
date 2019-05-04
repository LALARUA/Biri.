package cn.zx.biri.bookservice.controller;

import cn.zx.biri.bookservice.service.AuthorService;
import cn.zx.biri.common.pojo.entry.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 10:53 2019/4/8 0008
 */
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("allAuthors")
    public List<Author> allAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("authorDetail")
    public Author authorDetail(Integer authorId){
        Author author = authorService.authorDetail(authorId);
        return author;
    }

    @PutMapping("author")
    public String editAuthor(Author author){

        return "ss";
    }

    @PostMapping("author")
    public String insertAuthor(Author author){

        return "ss";
    }










}
