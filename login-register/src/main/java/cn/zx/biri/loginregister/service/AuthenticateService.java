package cn.zx.biri.loginregister.service;

import cn.zx.biri.common.pojo.vo.LoginVO;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:00 2019/2/24 0024
 */
public interface AuthenticateService {
    void authenticate(LoginVO loginVO) throws Exception;
    void registerNewUser(RegisterAndChangePasswordVO registerVO) throws Exception;
    void te();

}
