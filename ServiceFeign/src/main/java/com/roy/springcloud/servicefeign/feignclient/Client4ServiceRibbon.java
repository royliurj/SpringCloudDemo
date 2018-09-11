package com.roy.springcloud.servicefeign.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-ribbon")
public interface Client4ServiceRibbon {

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    String hello2();
}
