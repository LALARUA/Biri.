package cn.zx.biri.loginregister.service.serviceImpl;

import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.loginregister.service.AuthenticateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;


/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:47 2019/2/23 0023
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Override
    public void authenticate(String username, String password) throws Exception {
        Subject current = SecurityUtils.getSubject();
        System.out.println(current.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        current.login(token);

        //记住密码
        String value = username+"#"+password;
        HttpServletResponse currentHttpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();;
        CookieUtils.addCookie(currentHttpServletResponse,"user",value);
    }


}