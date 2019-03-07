package cn.zx.biri.common.pojo.entry;

public class BookWithBLOBs extends Book {
    private String summary;

    private String catalog;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog == null ? null : catalog.trim();
    }
}