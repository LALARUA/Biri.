package cn.zx.biri.zuul.config.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:46 2019/2/23 0023
 */
public interface AuthenticateService {
    void authenticateService(String username, String password, int rem) throws Exception;
}
