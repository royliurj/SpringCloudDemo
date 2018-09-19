package com.roy.springcloud.serviceribbon.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class StringPostCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
    private Integer id;

    public StringPostCommand(RestTemplate restTemplate, Integer id){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        //执行Post请求
        String result = restTemplate.postForObject("http://service-one/hello",id,String.class);

        //刷新缓存，清理缓存中失效的数据
        StringGetCommand.flushCache(id);
        return result;
    }
}
