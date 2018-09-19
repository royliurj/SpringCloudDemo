package com.roy.springcloud.serviceribbon.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.roy.springcloud.serviceribbon.command.UserCommand;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

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

    @GetMapping("/helloAsync")
    public String helloAsync() throws ExecutionException, InterruptedException {
        Future<String> future = ribbonService.helloAsync();
        return future.get();
    }

    @GetMapping("/helloCustom")
    public String helloCustom() throws ExecutionException, InterruptedException {
        UserCommand bookCommand = new UserCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
        //同步调用
        String result = bookCommand.execute();
//        //异步调用
//        Future<String> queue = bookCommand.queue();
//        String result2 = queue.get();
        return result;
    }

    @GetMapping("/hello222")
    public String hello222(){
        UserCommand bookCommand = new UserCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("helloGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("hello222"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("helloThread")), restTemplate);
        //同步调用
        String result = bookCommand.execute();
        return result;
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
