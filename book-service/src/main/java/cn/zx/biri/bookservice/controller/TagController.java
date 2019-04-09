package cn.zx.biri.bookservice.controller;

import cn.zx.biri.bookservice.service.TagService;
import cn.zx.biri.common.pojo.entry.Tag;

import cn.zx.biri.common.pojo.response.NewTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:54 2019/3/30 0030
 */
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("allTags")
    public String allTags(){
        return tagService.getAllTagHTML();
    }

    @PostMapping("tag")
    public String postTag(Tag tag){
        tagService.postTag(tag);
        return null;
    }


}
