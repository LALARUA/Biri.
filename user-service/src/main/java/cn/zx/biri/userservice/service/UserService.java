package cn.zx.biri.userservice.service;

import cn.zx.biri.common.pojo.Entry.User;
import cn.zx.biri.common.pojo.VO.LoginVO;
import cn.zx.biri.common.pojo.VO.RegisterAndChangePasswordVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:52 2019/2/28 0028
 */
public interface UserService {

    User authenticate(String username,String password);


    void insertUser(RegisterAndChangePasswordVO registerAndChangePasswordVO);


    User selectUserByUsername(String username);


    User authenticateUser(LoginVO loginVO);


    void updateUser(RegisterAndChangePasswordVO registerAndChangePasswordVO);
}
