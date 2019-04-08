package cn.zx.biri.common.pojo.response;

import cn.zx.biri.common.pojo.entry.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 9:17 2019/4/8 0008
 */
public class NewTag extends Tag {


    private Integer level;


   private List<NewTag> sonTags = new ArrayList<>();


   public void build(Tag tag){
       setfatherId(tag.getfatherId());
       setId(tag.getId());
       setName(tag.getName());
       setStatus(tag.getStatus());
   }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<NewTag> getSonTags() {
        return sonTags;
    }

    public void setSonTags(List<NewTag> sonTags) {
        this.sonTags = sonTags;
    }
}
