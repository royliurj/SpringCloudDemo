package com.roy.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${foo}")
    String foo;

    @GetMapping("/hello")
    public String hello(){
        return "hello " + foo;
    }
}
