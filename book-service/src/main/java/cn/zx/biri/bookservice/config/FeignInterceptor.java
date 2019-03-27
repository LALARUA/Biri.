package cn.zx.biri.bookservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:41 2019/2/28 0028
 */

@Component
public class FeignInterceptor implements RequestInterceptor {


    public void apply(RequestTemplate requestTemplate){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        requestTemplate.header("Cookie", request.getHeader("Cookie"));
    }
}