package cn.zx.biri.loginregister.test;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 22:40 2019/1/8 0008
 */
@Component
public class t1 {

    @Bean
    public ts2 ts2(){
        return new ts2();
    }

    @Bean ts2 ts3(){
        return new ts2();
    }
}
