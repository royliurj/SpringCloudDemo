package com.roy.springcloud.servicefeign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

//Feign在远程调用失败后会进行重试
@Configuration
public class FeignConfig {
    @Bean
    public Retryer retryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }

//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
//        return new BasicAuthRequestInterceptor("user","123456");
//    }
}
