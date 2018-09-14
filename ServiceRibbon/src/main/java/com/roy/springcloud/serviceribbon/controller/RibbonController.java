package com.roy.springcloud.serviceribbon.controller;

import com.roy.springcloud.serviceribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RibbonController {

    @Value("${server.port}")
    private String port;

    @Autowired
    RibbonService ribbonService;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello(){
        return ribbonService.hello();
    }

    @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancerClient.choose("service-one");
        return instance.getHost() + ":" + instance.getPort();
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "Hello World, i am from ribbon port: " + port;
    }

    @GetMapping("/test")
    public String testRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        //传参数
        restTemplate.getForObject("test.com/abc?param={1}",String.class,"didi");

        //传Map
        Map<String,String> param = new HashMap<>();
        param.put("name","test");
        restTemplate.getForObject("test.com/abc?param={name}",String.class,param);

        return restTemplate.getForObject("http://www.baidu.com",String.class);
    }
}
