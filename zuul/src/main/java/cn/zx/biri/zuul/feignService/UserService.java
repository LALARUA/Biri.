package cn.zx.biri.zuul.feignService;

import cn.zx.biri.common.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:03 2019/2/23 0023
 */
@FeignClient(value = "userService")
public interface UserService {
    @PostMapping("addNewUser")
    void addNewUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("selectUserByUsername")
    User selectUserByUsername(@RequestParam("username") String username);
}
