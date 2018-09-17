package com.roy.springcloud.servicefeign.feignclient;

import com.roy.springcloud.servicefeign.config.DisableHystrixConfig;
import org.springframework.cloud.netflix.feign.FeignClient;

//针对服务test禁用熔断功能
@FeignClient(name = "test", configuration = DisableHystrixConfig.class)
public interface HelloService {
}
