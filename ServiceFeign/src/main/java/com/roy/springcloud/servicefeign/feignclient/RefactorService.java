package com.roy.springcloud.servicefeign.feignclient;

import com.roy.springcloud.model.ServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

//直接继承接口即可
@FeignClient(value = "service-one")
public interface RefactorService extends ServiceApi {
}
