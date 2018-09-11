package com.roy.springcloud.servicefeign.feignclient;

import org.springframework.stereotype.Component;

@Component
public class HystrixError implements EurekaClientFeign {
    @Override
    public String hello() {
        return "hello error, sorry!!!";
    }
}
