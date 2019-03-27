package cn.zx.biri.webservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 14:22 2019/3/24 0024
 */
@Component
public class FeignInterceptor implements RequestInterceptor {


    public void apply(RequestTemplate requestTemplate){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        requestTemplate.header("Cookie", request.getHeader("Cookie"));
    }
}
