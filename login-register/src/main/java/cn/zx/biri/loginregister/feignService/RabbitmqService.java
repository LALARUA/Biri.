package cn.zx.biri.loginregister.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 18:00 2019/2/23 0023
 */
@FeignClient(value = "rabbitmq-service")
public interface RabbitmqService {
    @PostMapping("sendMessageToQueueDirect")
    String sendMessageToQueueDirect(@RequestParam(value = "routeKey") String routeKey, @RequestParam(value = "message") String message);


}
