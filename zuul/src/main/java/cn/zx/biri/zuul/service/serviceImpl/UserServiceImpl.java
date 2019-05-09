package cn.zx.biri.zuul.service.serviceImpl;

import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.common.pojo.example.UserExample;
import cn.zx.biri.common.pojo.vo.LoginVO;
import cn.zx.biri.common.pojo.vo.RegisterAndChangePasswordVO;

import cn.zx.biri.zuul.mapper.UserMapper;
import cn.zx.biri.zuul.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();;
//        HttpSession session = httpServletRequest.getSession();
//        String id = session.getId();
//        System.out.println(session.getAttribute("uu"));
//        session.setAttribute("user",user);
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
