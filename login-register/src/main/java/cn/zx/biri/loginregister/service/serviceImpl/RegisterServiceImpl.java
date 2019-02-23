package cn.zx.biri.loginregister.service.serviceImpl;

import cn.zx.biri.loginregister.feignService.RabbitmqService;
import cn.zx.biri.loginregister.service.RegisterService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:16 2019/2/22 0022
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RabbitmqService rabbitmqService;

    @Override
    public void registerNewUser(String email, String password) {
        rabbitmqService.sendMessageToQueueDirect("registerNewUser",email);
    }
}
