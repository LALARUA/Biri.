package cn.zx.biri.userservice.service;

import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.vo.LoginVO;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;

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


    void updateUser(User user);
}
