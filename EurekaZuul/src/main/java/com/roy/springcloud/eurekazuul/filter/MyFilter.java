package com.roy.springcloud.eurekazuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {

    private final String PRE_TYPE = "pre";

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    //过滤器的类型：pre、post、routing、error
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    //过滤顺序：值越小，越早执行
    @Override
    public int filterOrder() {
        return 0;
    }

    //表示该过滤器是否过滤逻辑，为true，则执行run方法
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //具体逻辑
    @Override
    public Object run() {
//        doSomething();

        //获取当前请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");

        if(accessToken == null){
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){
                return null;
            }
        }
        log.info("ok");
        return null;
    }

    private void doSomething(){
        throw new RuntimeException("Exit some errors");
    }
}
