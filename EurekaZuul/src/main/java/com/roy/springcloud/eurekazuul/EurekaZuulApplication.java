package com.roy.springcloud.eurekazuul;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.roy.springcloud.eurekazuul.config.FilterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties({FilterConfiguration.class})
@EnableZuulProxy    //开启路由网关
@EnableEurekaClient //开启Eureka客户端
@SpringBootApplication
public class EurekaZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaZuulApplication.class,args);
    }

    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

//    //动态加载器
//    @Bean
//    public FilterLoader filterLoader(FilterConfiguration filterConfiguration){
//        FilterLoader filterLoader = FilterLoader.getInstance();
//        filterLoader.setCompiler(new GroovyCompiler());
//
//        try {
//            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
//            FilterFileManager.init(filterConfiguration.getInterval(),filterConfiguration.getRoot() + "/pre",filterConfiguration.getRoot() + "/post");
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return filterLoader;
//    }
}
