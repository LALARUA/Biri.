package cn.zx.biri.bookservice.service;

import cn.zx.biri.common.pojo.entry.Tag;

import cn.zx.biri.common.pojo.response.NewTag;
import cn.zx.biri.common.utils.HandleBookTagUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:55 2019/3/30 0030
 */
public interface TagService {
     List<List<Tag>> tagLink(List<Tag> tags);
     String getAllTagHTML();
     Map getHead(String key);
     String postTag(Tag tag);
}
