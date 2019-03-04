package cn.zx.biri.userservice.service.serviceImpl;

import cn.zx.biri.common.pojo.Entry.User;
import cn.zx.biri.common.pojo.Entry.UserExample;
import cn.zx.biri.common.pojo.VO.LoginVO;
import cn.zx.biri.common.pojo.VO.RegisterAndChangePasswordVO;
import cn.zx.biri.userservice.mapper.UserMapper;
import cn.zx.biri.userservice.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 19:03 2019/2/28 0028
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User authenticate(String username, String password) {
        return null;

    }

    @Override
    public void insertUser(RegisterAndChangePasswordVO registerAndChangePasswordVO) {
        User user = new User();
        user.setEmail(registerAndChangePasswordVO.getUsername());
        String hashAlgorithmName = "MD5";
        Object credentials = registerAndChangePasswordVO.getPassword();
        Object salt = ByteSource.Util.bytes(user.getEmail());
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt);
        user.setPassword(String.valueOf(result));
        user.setNickname(user.getEmail());
        userMapper.insertSelective(user);
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();;
        HttpSession session = httpServletRequest.getSession();
        String id = session.getId();
        System.out.println(session.getAttribute("uu"));

        session.setAttribute("user",user);
    }


    @Override
    public User selectUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size()==0 ? null : users.get(0);
    }

    @Override
    public User authenticateUser(LoginVO loginVO) {
        return null;
    }

    @Override
    public void updateUser(RegisterAndChangePasswordVO registerAndChangePasswordVO) {

    }
}
