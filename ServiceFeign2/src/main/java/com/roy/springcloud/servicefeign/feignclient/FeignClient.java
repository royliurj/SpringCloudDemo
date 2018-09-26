package com.roy.springcloud.servicefeign.feignclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface FeignClient {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();
}
