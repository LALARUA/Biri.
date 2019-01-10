package cn.zx.biri.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:32 2019/1/9 0009
 */
public class CookieUtils {
    public static Cookie getCookieByName(HttpServletRequest httpServletRequest,String name){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies==null)
            return null;
        else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name))
                    return cookie;
            }
        }
        return null;
    }

    public static void foreachCookieName(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName()+" : "+cookie.getValue() );
            }
        }
    }
}
