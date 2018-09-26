package com.roy.springcloud.servicefeign.controller;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import(FeignClientsConfiguration.class)
@RestController
public class HelloController {

    private com.roy.springcloud.servicefeign.feignclient.FeignClient userFeignClient;
    private com.roy.springcloud.servicefeign.feignclient.FeignClient adminFeignClient;

    @Autowired
    public HelloController(Encoder encoder, Decoder decoder, Client client, Contract contract){
        this.userFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1"))
                .target(com.roy.springcloud.servicefeign.feignclient.FeignClient.class,"http://service-two/");

        this.adminFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2"))
                .target(com.roy.springcloud.servicefeign.feignclient.FeignClient.class,"http://service-two/");
    }

    @GetMapping("/user/hello")
    public String hello(){
        return userFeignClient.hello();
    }

    @GetMapping("/admin/hello")
    public String hello2(){
        return adminFeignClient.hello();
    }
}
