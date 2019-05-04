package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.mapper.TagMapper;
import cn.zx.biri.bookservice.service.TagService;
import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.example.TagExample;

import cn.zx.biri.common.pojo.response.NewTag;
import cn.zx.biri.common.utils.HandleBookTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:55 2019/3/30 0030
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Autowired
    CacheManager cacheManager;
    public List<List<Tag>> tagLink(List<Tag> tags){
        return HandleBookTagUtils.tagLink(tags);
    }


//    @Cacheable(cacheNames = "allTags",key = "#key")
    public String getAllTagHTML(){
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        StringBuilder HTML = new StringBuilder();
        HandleBookTagUtils.getTagHTML( (NewTag)HandleBookTagUtils.getHead(tags).get("tagHead"),HTML);
        return HTML.toString();
    }


    @Cacheable(cacheNames = "importantCache",key = "#key")
    public Map getHead(String key){
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        Map map = HandleBookTagUtils.getHead(tags);
        return map;
    }

    @Override
    public String postTag(Tag tag) {
        Cache cache = cacheManager.getCache("importantCache");
        cache.evict("allTags");
        cache.evict("tags");
        return null;
    }

}
