package com.roy.springcloud.serviceribbon.config;

import com.roy.springcloud.customConfig.ServiceOneCustomConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "service-one", configuration = ServiceOneCustomConfig.class)
public class ServiceOneConfig {
}
