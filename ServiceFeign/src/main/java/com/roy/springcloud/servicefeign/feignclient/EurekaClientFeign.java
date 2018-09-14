package com.roy.springcloud.servicefeign.feignclient;

import com.roy.springcloud.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@FeignClient(value = "service-one", fallback = HystrixError.class)
public interface EurekaClientFeign {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hello();

    @GetMapping("/user")
    User hello2(@RequestHeader("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/user2")
    String hello3(@RequestBody User user);
}
