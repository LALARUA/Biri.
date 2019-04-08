package cn.zx.biri.bookservice.controller;

import cn.zx.biri.bookservice.service.AuthorService;
import cn.zx.biri.common.pojo.entry.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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






}
