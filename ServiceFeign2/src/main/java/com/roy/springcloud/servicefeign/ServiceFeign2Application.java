package com.roy.springcloud.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard //开启基于注解的熔断器仪表盘功能
@EnableEurekaClient //开启基于注解的Eureka客户端功能
@SpringBootApplication
public class ServiceFeign2Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeign2Application.class,args);
    }
}
