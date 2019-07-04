package cn.zx.biri.loginregister.service.serviceImpl;

import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.response.BookInCart;
import cn.zx.biri.common.pojo.vo.LoginVO;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;
import cn.zx.biri.common.utils.CookieUtils;
import cn.zx.biri.loginregister.feignService.OrderAndCartService;
import cn.zx.biri.loginregister.feignService.RabbitmqService;
import cn.zx.biri.loginregister.feignService.UserService;
import cn.zx.biri.loginregister.service.AuthenticateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:47 2019/2/23 0023
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {
    @Autowired
    RabbitmqService rabbitmqService;

    @Autowired
    UserService userService;

    @Autowired
    OrderAndCartService orderAndCartService;

    @Override
    public void registerNewUser(RegisterAndChangePasswordVO registerVO) throws Exception {
        userService.insertUser(registerVO);
        rabbitmqService.sendMessageToQueueDirect("registerNewUser",registerVO.getUsername());
        authenticate(registerVO);
    }

    @Override
    public void authenticate(LoginVO loginVO) throws Exception {
        Subject current = SecurityUtils.getSubject();
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        current.login(token);
        //记住密码
        String value = username+"#"+password;
        HttpServletResponse currentHttpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest currentHttpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CookieUtils.addCookie(currentHttpServletResponse,"user",value);
        //将认证后的用户放入session
        HttpSession httpSession = currentHttpServletRequest.getSession();
        User user = userService.selectUserByUsername(username);
        httpSession.setAttribute("user",user);
    }




}
