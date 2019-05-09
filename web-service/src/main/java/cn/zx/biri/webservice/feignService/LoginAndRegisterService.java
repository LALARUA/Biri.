package cn.zx.biri.webservice.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:52 2019/2/27 0027
 */
@FeignClient("login-register")
public interface LoginAndRegisterService {
    @PostMapping("authenticate")
    Map authenticate(@RequestParam("username") String username,@RequestParam("password") String password);

    @GetMapping("logout")
    void logout();
}
