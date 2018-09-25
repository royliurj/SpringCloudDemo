package com.roy.springcloud.zipkenserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

//@EnableZipkinServer //开启Zipkin功能
@EnableZipkinStreamServer
@EnableEurekaClient
@SpringBootApplication
public class ZipkenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkenServerApplication.class,args);
    }
}

