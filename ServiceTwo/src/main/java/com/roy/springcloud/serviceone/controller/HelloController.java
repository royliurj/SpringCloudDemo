package com.roy.springcloud.serviceone.controller;

import com.roy.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority c : collection) {
                logger.info("当前用户是"+user.getUsername()+"，角色是"+c.getAuthority());
            }
        }

//        discoveryClientDemo();

        return "Hello World, i am from port: " + port;
    }

    @GetMapping("/user")
    public User user1(@RequestHeader String name, @RequestParam Integer age){
        return new User(name,age);
    }

    @PostMapping("/user2")
    public String user2(@RequestBody User user){
        return "Hello " + user.getName() + ", Age: " + user.getAge();
    }

    private void discoveryClientDemo(){
        //获取所有服务名
        List<String> services = discoveryClient.getServices();
        logger.info("所有的服务：" + Arrays.toString(new List[]{services}));

        //根据服务名获取服务实例
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);

            for (ServiceInstance instanceItem: instances) {
                logger.info("HOST: " + instanceItem.getHost() + ", PORT" + instanceItem.getPort() + ", ServiceID: " + instanceItem.getServiceId() + ", URI:" + instanceItem.getUri());
            }
        }

        //获取本地服务实例，已经启用
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
    }
}
