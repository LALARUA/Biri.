package cn.zx.biri.webservice.feignService;

import cn.zx.biri.common.pojo.vo.SelectBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:31 2019/3/13 0013
 */
@FeignClient(value = "book-service")
public interface BookService {
    @GetMapping(value = "bookList",consumes = MediaType.APPLICATION_JSON_VALUE)
    Map bookList(@RequestBody SelectBook condition);


}
