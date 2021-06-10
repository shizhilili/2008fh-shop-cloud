package com.fh.shop.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class CrossFilter extends ZuulFilter {
    /**
     * pre：所有的客户端请求再犯耐高温 真正微服务 前执行
     * route：
     * post：在 访问微服务 之后执行
     * error：
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 执行顺序 数值越小 优先等级越高   就先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前的过滤器是否开启
     * true：开启   false：关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 所有的业务逻辑处理
     * 永远返回null
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();
        //解决跨域
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"*");

        //解决自定义heanders
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"x-auth,content-type,x-token");
        //解决特殊请求方式 delete post get put
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"DELETE,POST,GET,PUT");

        //解决 options 请求
        //获取请求方式 get post delete options put
        HttpServletRequest request = currentContext.getRequest();
        String methodHTTP = request.getMethod();
        if(methodHTTP.equalsIgnoreCase("OPTIONS")){
            //不处理 进行拦截
            currentContext.setSendZuulResponse(false);//进行拦截 不再进行路由转发
            return null;
        }

        return null;
    }
}
