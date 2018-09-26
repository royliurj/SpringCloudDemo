package com.roy.springcloud.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.roy.springcloud.serviceribbon.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
import java.util.logging.Logger;

@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    //通过HystrixCommand注解为方法hello开启熔断器功能, fallbackMethod处理回退逻辑，本例中直接返回一个字符串
    @HystrixCommand(fallbackMethod = "helloError", ignoreExceptions = CustomException.class)
    public String hello(){
        return restTemplate.getForObject("http://service-one/hello",String.class);
    }

    public String hello4(){
        return restTemplate.getForObject("http://service-two/hello",String.class);
    }

    @HystrixCommand(commandKey = "hello2", groupKey = "helloGroup", threadPoolKey = "helloThread")
    public String hello2(){
        return "asdf";
    }

    @HystrixCommand(commandKey = "helloKey", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String hello3(){
        return "asdf";
    }

    //注解方式异步实现
    @HystrixCommand(fallbackMethod = "helloError")
    public Future<String> helloAsync(){
        return new com.netflix.hystrix.contrib.javanica.command.AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://service-one/hello",String.class);
            }
        };
    }

    public String helloDefaultError(){
        //这里可能也调用一个远程服务
        return "asdfasdf";
    }

    //回调里如果也需要远程调度其它的服务，最好在远程调取其它服务时也加上熔断功能
    @HystrixCommand(fallbackMethod = "helloDefaultError")
    public String helloError(Throwable e){
        Logger.getLogger(String.valueOf(RibbonService.class)).info(e.getMessage());
        return "hello error, sorry!";
    }
}
