package com.roy.springcloud.serviceone.controller;

import com.roy.springcloud.model.ServiceApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorController implements ServiceApi {

    //这里不需要添加@RequestMapping，直接从接口中继承
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
