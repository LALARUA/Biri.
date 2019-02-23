package cn.zx.biri.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:32 2019/1/9 0009
 */
public class CookieUtils {
    public static Cookie getCookieByName(HttpServletRequest httpServletRequest,String name){
        Cookie[] cookies = httpServletRequest.getCookies();
        if (Objects.isNull(cookies))
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

    public static Cookie addCookie(HttpServletResponse httpServletResponse,String cookieName,String cookieValue){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60*2);
        httpServletResponse.addCookie(cookie);
        return cookie;
    }
}
