package cn.zx.biri.zuul.service;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 16:00 2019/2/24 0024
 */
public interface AuthenticateService {
    public void authenticate(String username, String password) throws Exception;
}
