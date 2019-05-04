package cn.zx.biri.common.utils;

import cn.zx.biri.common.pojo.entry.Tag;
import cn.zx.biri.common.pojo.response.NewTag;


import java.util.*;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 17:25 2019/3/22 0022
 */
public class HandleBookTagUtils {

    private List<List<Tag>> tagLink = new ArrayList<>();

    public List<List<Tag>> getTagLink() {
        return tagLink;
    }

    public void setTagLink(List<List<Tag>> tagLink) {
        this.tagLink = tagLink;
    }

    public static Map getHead(List<Tag> tags){
        Map<Integer,NewTag> tagMap = new HashMap<>();
        Map map = new HashMap();
        Tag head = null;
        for (Tag tag : tags) {
            NewTag newTag = new NewTag();
            newTag.build(tag);
            tagMap.put(tag.getId(),newTag);
            if (tag.getfatherId()==0)
                head = tagMap.get(tag.getId());
        }
        for (Tag t : tags) {
            NewTag father =  tagMap.get(t.getfatherId());
            if (father==null)
                continue;
            father.getSonTags().add(tagMap.get(t.getId()));
        }
        setTagLevel(head,0);
        map.put("tagHead",head);
        map.put("tagMap",tagMap);
        return map;
    }
    public static void setTagLevel(Tag tag,int level){
        if (tag==null)
            return;
        ((NewTag) tag).setLevel(level);
        for (Tag t : ((NewTag) tag).getSonTags()) {
            setTagLevel(t,++level);
        }
    }
    public static List<List<Tag>> tagLink(List<Tag> tags){
        Tag head = (NewTag)getHead(tags).get("tagHead");
        HandleBookTagUtils handBookTagUtils = new HandleBookTagUtils();
        handBookTagUtils.createLink(head,new LinkedList());
        return handBookTagUtils.getTagLink();
    }

    public void createLink(Tag head,List list){
        list.add(head);
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

    public static void getTagHTML(Tag head,StringBuilder stringBuilder){
       out:if (((NewTag)head).getSonTags()!=null&&((NewTag)head).getSonTags().size()>0){
           if (head.getfatherId()==0)
               break out;
            stringBuilder.append(" <li class=\"mainmenu__item menu-item-has-children has-children\">\n");
            stringBuilder.append("<a href=\"/Biri/book/list?tagIds="+head.getId()+"&tagName="+head.getName()+"\" class=\"mainmenu__link\">\n");
            stringBuilder.append("  <span class=\"mm-text\">"+ head.getName()+"</span>\n");
            stringBuilder.append(" </a>\n");
            stringBuilder.append(" <ul class=\"sub-menu\">\n");
        }
        else {
            stringBuilder.append(" <li>\n");
            stringBuilder.append("<a href=\"/Biri/book/list?tagIds="+ head.getId()+"&tagName="+head.getName()+"\" class=\"mainmenu__link\">\n");
            stringBuilder.append("  <span class=\"mm-text\">"+ head.getName()+"</span>\n");
            stringBuilder.append(" </a>\n");
            stringBuilder.append("</li>\n");
            return;
        }
        for (Tag tag : ((NewTag)head).getSonTags()) {
            getTagHTML(tag,stringBuilder);
        }
        if (((NewTag)head).getSonTags()!=null&&head.getfatherId()!=0&&((NewTag)head).getSonTags().size()>0){
            stringBuilder.append("</ul>\n");
            stringBuilder.append("</li>\n");
        }
    }
}
