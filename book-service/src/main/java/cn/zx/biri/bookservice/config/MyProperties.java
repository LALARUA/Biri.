package cn.zx.biri.bookservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:09 2019/3/17 0017
 */
@ConfigurationProperties(prefix = "book.properties")
public class MyProperties {



    private String windowBookImagePathPrefix;
    private String linuxBookImagePathPrefix;

    public String getLinuxBookImagePathPrefix() {
        return linuxBookImagePathPrefix;
    }

    public void setLinuxBookImagePathPrefix(String linuxBookImagePathPrefix) {
        this.linuxBookImagePathPrefix = linuxBookImagePathPrefix;
    }

    public String getWindowBookImagePathPrefix() {
        return windowBookImagePathPrefix;
    }

    public void setWindowBookImagePathPrefix(String windowBookImagePathPrefix) {
        this.windowBookImagePathPrefix = windowBookImagePathPrefix;
    }
}
