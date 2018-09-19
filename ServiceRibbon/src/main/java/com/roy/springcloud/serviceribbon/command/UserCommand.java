package com.roy.springcloud.serviceribbon.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;

    public UserCommand(Setter setter, RestTemplate restTemplate){
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
//        return restTemplate.getForObject("http://localhost:8001/hello",String.class);
        return restTemplate.getForObject("http://service-one/hello",String.class);
    }

    @Override
    protected String getFallback() {
        Throwable throwable = getExecutionException();
        System.out.println(throwable.getMessage());

        return "i am error222!";
    }

    //Hystrix会根据这个方法返回到值区分是否是重复的请求。
    @Override
    protected String getCacheKey() {
        return "";
    }
}
