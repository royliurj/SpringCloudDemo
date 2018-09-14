package com.roy.springcloud.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/refactor")
public interface ServiceApi {

    @GetMapping("/hello4")
    String hello(@RequestParam("name") String name);
}
