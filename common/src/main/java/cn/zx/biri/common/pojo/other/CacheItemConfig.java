package cn.zx.biri.common.pojo.other;

import java.io.Serializable;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:55 2019/3/17 0017
 */
public class CacheItemConfig implements Serializable {

    /**
     * 缓存容器名称
     */
    private String name;
    /**
     * 缓存失效时间
     */
    private long expiryTimeSecond;
    /**
     * 当缓存存活时间达到此值时，主动刷新缓存
     */
    private long preLoadTimeSecond;
}
