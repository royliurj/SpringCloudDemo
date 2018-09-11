package com.roy.springcloud.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    //为方法hello开启熔断器功能
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(){
        return restTemplate.getForObject("http://service-one/hello",String.class);
    }

    public String helloError(){
        return "hello error, sorry!";
    }
}
