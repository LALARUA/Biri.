package cn.zx.biri.webservice.service;

import cn.zx.biri.common.pojo.response.BookEnhanced;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:45 2019/3/30 0030
 */
public interface BBookService {

    String tagsHTML(String key);

    Map<Integer,BookEnhanced> allBookList(String key);


}
