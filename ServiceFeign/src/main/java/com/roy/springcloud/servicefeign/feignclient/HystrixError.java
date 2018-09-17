package com.roy.springcloud.servicefeign.feignclient;

import com.roy.springcloud.model.User;
import org.springframework.stereotype.Component;

//服务降级容错类
@Component
public class HystrixError implements EurekaClientFeign {
    @Override
    public String hello() {
        return "hello error, sorry!!!";
    }

    @Override
    public User hello2(String name, Integer age) {
        return null;
    }

    @Override
    public String hello3(User user) {
        return "i am error";
    }
}
