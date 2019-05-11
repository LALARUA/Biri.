package cn.zx.biri.userservice.controller;


import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;
import cn.zx.biri.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:49 2019/2/28 0028
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("updateUserPassword")
    public void updateUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO){
        User user = new User();
        user.setPassword(registerAndChangePasswordVO.getPassword());
        userService.updateUser(user);
        return;
    }

    @GetMapping("selectUserByUsername")
    public User selectUserByUsername(@RequestParam("username") String username){
       return userService.selectUserByUsername(username);
    }

    @PostMapping("insertUser")
    public void insertUser(@RequestBody RegisterAndChangePasswordVO registerAndChangePasswordVO){
        userService.insertUser(registerAndChangePasswordVO);
    }

    @PutMapping("applyAdmin")
    public void applyAdmin(Integer userId){
        User user = new User();
        user.setId(userId);
        userService.updateUser(user);

    }

}
