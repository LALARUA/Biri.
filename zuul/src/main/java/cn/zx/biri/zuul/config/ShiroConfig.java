package cn.zx.biri.zuul.config;



import cn.zx.biri.common.pojo.entry.User;
import cn.zx.biri.zuul.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@Configuration
public class ShiroConfig {
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("745402208@qq.com");
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt);
        System.out.println(result);
    }
    class ShiroRealm extends AuthorizingRealm {
        @Autowired
        UserService userService;
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
            String email = String.valueOf(primaryPrincipal);
            Set<String> roles = new HashSet<String>();
            User user = userService.selectUserByUsername(email);
            roles.add(user.getRole());
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
            return simpleAuthorizationInfo;
        }
        @Override
        protected org.apache.shiro.authc.AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            String username = token.getUsername();
            User user = userService.selectUserByUsername(username);
            if (user==null)
                throw new UnknownAccountException();
            String password = user.getPassword();
            ByteSource salt = ByteSource.Util.bytes(username);
            return new SimpleAuthenticationInfo(username,password,salt,getName());
        }
    }
    /*
    配置LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
    负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
    主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //配置CacheManager，缓存管理器
    @Bean(name = "cacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public CacheManager cacheManager() {
        return new EhCacheManager();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager());
        securityManager.setRealm(shiroRealm());

//        DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
//        defaultSessionManager.setDeleteInvalidSessions(false);
//        securityManager.setSessionManager(defaultSessionManager);
        return securityManager;
    }

    //配置 DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }


    //shiroFilter入口来拦截需要安全控制的url
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = new LinkedHashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/");
//        filters.put("logout",null);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();

        shiroFilterFactoryBean.setSuccessUrl("/homepage");
        shiroFilterFactoryBean.setLoginUrl("/Biri/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        //.anon可以被匿名访问
        //.authc必须认证(即登陆)
        // /**拦截所有资源

        filterChainDefinitionManager.put("/loginAndRegister/**","anon");
//        filterChainDefinitionManager.put("/Biri/login","anon");
//        filterChainDefinitionManager.put("/Biri/home","anon");
//        filterChainDefinitionManager.put("/assets/**","anon");
//        filterChainDefinitionManager.put("/static/assets/**","anon");
        filterChainDefinitionManager.put("/Biri/user/**","authc");
        filterChainDefinitionManager.put("/orderCart/**","authc");
        filterChainDefinitionManager.put("/commentService/**","authc");
        filterChainDefinitionManager.put("/Biri/","authc");
        filterChainDefinitionManager.put("/Biri/admin/**","roles[admin]");
        filterChainDefinitionManager.put("/**","anon");




//        filterChainDefinitionManager.put("/","roles[user]");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        return shiroFilterFactoryBean;
    }


    //密码盐
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;

    }

//    ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
//    负责用户的认证和权限的处理
    @Bean(name = "ShiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }
}

