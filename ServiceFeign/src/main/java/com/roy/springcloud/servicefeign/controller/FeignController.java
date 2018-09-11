package com.roy.springcloud.servicefeign.controller;

import com.roy.springcloud.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/hello")
    public String hello(){
        return feignService.hello();
    }
}
