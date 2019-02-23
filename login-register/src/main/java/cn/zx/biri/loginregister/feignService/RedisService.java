package cn.zx.biri.loginregister.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:41 2019/1/13 0013
 */
@FeignClient(value = "redis-service")
public interface RedisService {

    @GetMapping("get")
    public String get(@RequestParam(value = "name") String name);


    }
