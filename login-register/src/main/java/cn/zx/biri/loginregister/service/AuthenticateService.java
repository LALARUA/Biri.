package cn.zx.biri.loginregister.service;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:00 2019/2/24 0024
 */
public interface AuthenticateService {
    void authenticate(String username, String password) throws Exception;
    void registerNewUser(String email,String password);
    void te();

}
