package cn.zx.biri.bookservice.service.serviceImpl;

import cn.zx.biri.bookservice.mapper.TagMapper;
import cn.zx.biri.bookservice.service.TagService;
import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.example.TagExample;
import cn.zx.biri.common.utils.HandleBookTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:55 2019/3/30 0030
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    public List<List<Tag>> tagLink(List<Tag> tags){
        return HandleBookTagUtils.tagLink(tags);
    }


//    @Cacheable(cacheNames = "allTags",key = "#key")
    public String getAllTagHTML(){
        TagExample tagExample = new TagExample();
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        StringBuilder HTML = new StringBuilder();
        HandleBookTagUtils.getTagHTML( HandleBookTagUtils.getHead(tags),HTML);
        return HTML.toString();
    }

}
