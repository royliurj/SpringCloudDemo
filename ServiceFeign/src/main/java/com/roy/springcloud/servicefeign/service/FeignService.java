package com.roy.springcloud.servicefeign.service;

import com.roy.springcloud.model.User;
import com.roy.springcloud.servicefeign.feignclient.Client4ServiceRibbon;
import com.roy.springcloud.servicefeign.feignclient.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    @Autowired
    Client4ServiceRibbon client4ServiceRibbon;

    public String hello(){
        return eurekaClientFeign.hello();
    }

    public String hello2(){
        return client4ServiceRibbon.hello2();
    }

    public User user(String name, Integer age){
        return eurekaClientFeign.hello2(name,age);
    }

    public String user2(User user){
        return eurekaClientFeign.hello3(user);
    }
}
