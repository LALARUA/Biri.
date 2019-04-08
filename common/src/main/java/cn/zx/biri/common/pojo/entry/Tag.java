package cn.zx.biri.common.pojo.entry;

import java.io.Serializable;

public class Tag implements Serializable {
    private Integer id;

    private String name;

    private Integer status;

    private Integer fatherId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getfatherId() {
        return fatherId;
    }

    public void setfatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }
}