package com.roy.springcloud.servicefeign.controller;

import com.roy.springcloud.model.User;
import com.roy.springcloud.servicefeign.feignclient.RefactorService;
import com.roy.springcloud.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @Autowired
    RefactorService refactorService;

    @GetMapping("/hello")
    public String hello(){
        return feignService.hello();
    }

    @GetMapping("/hell2")
    public String hello2(){
        return feignService.hello2();
    }

    @GetMapping("/user")
    public String user(){
        StringBuilder sb = new StringBuilder();

        sb.append(feignService.user("AAA",22)).append("\n");
        sb.append(feignService.user2(new User("BBB",33))).append("\n");
        return sb.toString();
    }

    @GetMapping("/hello4")
    public String helloRefacotr(){
        return refactorService.hello("CCC");
    }
}
