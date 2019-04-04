package cn.zx.biri.ordercart.feignService;

import cn.zx.biri.common.pojo.entry.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:56 2019/3/28 0028
 */
@FeignClient(value = "book-service")
public interface BookService {
    @GetMapping("reduceStock")
    int reduceStock();
}
