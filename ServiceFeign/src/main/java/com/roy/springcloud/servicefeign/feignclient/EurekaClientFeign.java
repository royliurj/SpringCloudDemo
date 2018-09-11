package com.roy.springcloud.servicefeign.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-one", fallback = HystrixError.class)
public interface EurekaClientFeign {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello();
}
