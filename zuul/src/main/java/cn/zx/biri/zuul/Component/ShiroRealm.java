package cn.zx.biri.zuul.Component;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @description shiroRealm配置
 * @author xiangXX
 * @date 2018/6/17 0017 10:10
  * @param
 *
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        String email = String.valueOf(primaryPrincipal);
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String email = token.getUsername();
//        Object username = email;
//        Object password = "123456";
//        ByteSource salt = ByteSource.Util.bytes(email);
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, salt, getName());
//        return info;
        String username = token.getUsername();
        ByteSource salt = ByteSource.Util.bytes(username);
        String password = "770c3f892bc1dc0d815ab4e18115481e";
        return new SimpleAuthenticationInfo(username,password,salt,getName());
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("zhongxiang");
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt);
        System.out.println(result);
    }
}
