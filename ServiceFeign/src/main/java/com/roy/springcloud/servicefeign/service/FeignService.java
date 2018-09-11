package com.roy.springcloud.servicefeign.service;

import com.roy.springcloud.servicefeign.feignclient.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    public String hello(){
        return eurekaClientFeign.hello();
    }
}
