package cn.zx.biri.common.utils;

import cn.zx.biri.common.pojo.entry.Tag;

import java.util.*;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:25 2019/3/22 0022
 */
public class HandleBookTagUtils {
    static class NewTag extends Tag{
        Tag tag;

        List<Tag> sonTags = new ArrayList<>();


        public NewTag(Tag tag) {
            this.tag = tag;
        }

        public Tag getTag() {
            return tag;
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public List<Tag> getSonTags() {
            return sonTags;
        }

        public void setSonTags(List<Tag> sonTags) {
            this.sonTags = sonTags;
        }
    }
    private List<List<Tag>> tagLink = new ArrayList<>();

    public List<List<Tag>> getTagLink() {
        return tagLink;
    }

    public void setTagLink(List<List<Tag>> tagLink) {
        this.tagLink = tagLink;
    }

    public static List<List<Tag>> tagLink(List<Tag> tags){
        Map<Integer,Tag> map = new HashMap<>();
        Tag head = null;
        for (Tag tag : tags) {

            map.put(tag.getId(),new NewTag(tag));
            if (tag.getfatherId()==0)
                head = map.get(tag.getId());
        }
        for (Tag t : tags) {
            NewTag father = (NewTag) map.get(t.getfatherId());
            if (father==null)
                continue;
            father.getSonTags().add(map.get(t.getId()));
        }
        HandleBookTagUtils handBookTagUtils = new HandleBookTagUtils();
        handBookTagUtils.createLink(head,new LinkedList());
        return handBookTagUtils.getTagLink();

    }

    public void createLink(Tag head,List list){
        NewTag headTag = (NewTag)head;
        list.add(headTag.getTag());
        NewTag newTag = (NewTag) head;
        if (newTag.getSonTags().size()==0){
            LinkedList linkedList = new LinkedList(list);
            linkedList.removeFirst();
            tagLink.add(linkedList);
            return;
        }
        for (Tag tag : newTag.getSonTags()) {
            int start = list.size();
            createLink(tag,list);
            int end = list.size();
            for (int i = start; i < end; i++) {
                list.remove(i);
            }
        }
    }
}
