package cn.zx.biri.common.pojo.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:11 2019/3/29 0029
 */
public class SelectBookCondition {
   static class Keyword{
        private String keyword;
        private int logic;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }
    }
    private String keyword;
    private Keyword authorId;
    private Keyword title;
    private Set<Keyword> tagIds;
    private Keyword publisher;
    private Integer isbn;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Keyword getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Keyword authorId) {
        this.authorId = authorId;
    }

    public Keyword getTitle() {
        return title;
    }

    public void setTitle(Keyword title) {
        this.title = title;
    }

    public Set<Keyword> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<Keyword> tagIds) {
        this.tagIds = tagIds;
    }

    public Keyword getPublisher() {
        return publisher;
    }

    public void setPublisher(Keyword publisher) {
        this.publisher = publisher;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }
}
