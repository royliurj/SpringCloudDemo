package com.roy.springcloud.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    //通过HystrixCommand注解为方法hello开启熔断器功能, fallbackMethod处理回退逻辑，本例中直接返回一个字符串
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(){
        return restTemplate.getForObject("http://service-one/hello",String.class);
    }

    //回调里如果也需要远程调度其它的服务，最好在远程调取其它服务时也加上熔断功能
    public String helloError(){
        return "hello error, sorry!";
    }
}
