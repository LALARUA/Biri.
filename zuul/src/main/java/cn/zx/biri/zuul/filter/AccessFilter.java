package cn.zx.biri.zuul.filter;

import cn.zx.biri.common.utils.CookieUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Objects;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 15:19 2019/1/10 0010
 */
@Component
public class AccessFilter extends ZuulFilter{
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;

    private static int index = 0;
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public boolean shouldFilter() {

        return true;
    }


    private String base64Encode(String base64Value) {
        try {

            byte[] decodedCookieBytes = Base64.getEncoder().encode(base64Value.getBytes());
            return new String(decodedCookieBytes);
        }
        catch (Exception e) {
            return null;
        }
    }
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL);
        if (requestURL.lastIndexOf(".js")!=-1||requestURL.lastIndexOf(".css")!=-1){
            return null;
        }
        HttpSession session = request.getSession();
//        Cookie[] cookies = request.getCookies();
        String sessionId = session.getId();
        String base64EncodeSessionId = base64Encode(sessionId);
        String cookie = request.getHeader("Cookie");
        if (Objects.isNull(cookie)){
            cookie = "SESSION="+base64EncodeSessionId;
        }
        else {
            String newCookie = "SESSION="+base64EncodeSessionId;
           cookie = cookie.replaceAll("\\bSESSION=.{48}",newCookie);
        }
        currentContext.addZuulRequestHeader("Cookie", cookie);
//        currentContext.addZuulRequestHeader("Cookie","SESSION=" + base64Encode(session.getId()));

//        currentContext.setSendZuulResponse(true);
//        currentContext.setResponseStatusCode(200);
//        String id = session.getId();
//        System.out.println("进入zuul"+(++index));
//        RequestContext ctx = RequestContext.getCurrentContext();
//        String sessionId = httpServletRequest.getSession().getId();
//        ctx.addZuulRequestHeader("Cookie", "SESSION=" + sessionId);
//        ctx.setSendZuulResponse(true);// 对该请求进行路由
//        ctx.setResponseStatusCode(200); // 返回200正确响应
        return null;
    }
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
