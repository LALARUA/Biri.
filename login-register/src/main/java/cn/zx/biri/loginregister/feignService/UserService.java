package cn.zx.biri.loginregister.feignService;

import cn.zx.biri.common.pojo.Entry.User;
import cn.zx.biri.common.pojo.VO.RegisterAndChangePasswordVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:03 2019/2/23 0023
 */
@FeignClient(value = "userService")
public interface UserService {
    @PostMapping("insertUser")
    void insertUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO);

    @GetMapping("selectUserByUsername")
    User selectUserByUsername(@RequestParam("username") String username);

    @PostMapping("updateUser")
    void updateUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO);
}
