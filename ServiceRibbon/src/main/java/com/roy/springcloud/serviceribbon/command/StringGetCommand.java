package com.roy.springcloud.serviceribbon.command;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class StringGetCommand extends HystrixCommand<String> {

    private static final HystrixCommandKey Getter_Key = HystrixCommandKey.Factory.asKey("CommandKey");
    private Integer id;

    private RestTemplate restTemplate;

    public StringGetCommand(RestTemplate restTemplate, Integer id){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
                .andCommandKey(Getter_Key)
                //设置属性
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://service-one/hello",String.class);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void flushCache(Integer id){
        //刷新缓存，根据Id进行清理
        HystrixRequestCache.getInstance(Getter_Key, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
