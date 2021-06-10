package com.fh.shop.filter;

import com.alibaba.fastjson.JSON;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.common.ResponseEnum;
import com.fh.shop.common.SecretLogin;
import com.fh.shop.common.ServerResponse;
import com.fh.shop.util.KeyUtil;
import com.fh.shop.util.Md5Util;
import com.fh.shop.util.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class JwtFilter extends ZuulFilter {

    @Value("${fh.shop.checkUrls}")
    private List<String> checkUrls;

    @Override
    public String filterType() {

        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object run() throws ZuulException {
    log.info("---------------------{}",checkUrls);
        //获取当前访问的url
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //http://localhoet:9000/xx/xx
        StringBuffer requestURL = request.getRequestURL();
       boolean isCheck =false;
        for (String checkUrl : checkUrls) {
            if (requestURL.indexOf(checkUrl)>0){
                isCheck=true;
                break;
            }
        }
        if (!isCheck){
            //默认放行
            return null;
        }
        //需要进行验证
        //判断是否能够获取头信息
        String header = request.getHeader("x-auth");
        //判断有没有头信息
        if(header==null){
           return buildResponse(ResponseEnum.TOKEN_HEADER_IS_NULL);
        }
        //判断头信息是否完整
        String[] headerArr = header.split("\\.");
        if(headerArr.length!=2){
            return buildResponse(ResponseEnum.TOKEN_HEADER_IS_FULL);
        }
        //然后就可以进行验签
        //获取签名
        String memberVo64 = headerArr[0];
        String sout64 = headerArr[1];
        String member = new String(Base64.getDecoder().decode(memberVo64),"utf-8");
        String sout = new String(Base64.getDecoder().decode(sout64),"utf-8");
        if(!Md5Util.sout(member,SecretLogin.SECRE).equals(sout)){
           return buildResponse(ResponseEnum.TOKEN_HEADER_ERROR);
        }
        //将json转化为java对象
         MemberVo membervo = JSON.parseObject(member, MemberVo.class);
        Long id = membervo.getId();
        Boolean ex = RedisUtil.exist(KeyUtil.getksy(id));
        //判断是否过期
        if(!ex){
           return buildResponse(ResponseEnum.TOKEN_IS_NULL);
        }
        //续命
        RedisUtil.expire(KeyUtil.getksy(id),SecretLogin.SECODE);
        //将membervo存到request中
        //request.setAttribute(SecretLogin.MEMVO,membervo)
        //将要传给后台微服务的信息放到请求头中

        currentContext.addZuulRequestHeader(SecretLogin.MEMVO,URLEncoder.encode(member,"utf-8"));

        return null;
    }

    private Object buildResponse(ResponseEnum responseEnum) {
        RequestContext currentContext = RequestContext.getCurrentContext();
        //不仅拦截 还不允许向后面粥 同时给前台返回提示
        currentContext.setSendZuulResponse(false);//进行拦截 不再进行路由转发
        //提示json格式的信息
        ServerResponse error = ServerResponse.error(responseEnum);
        String jsonString = JSON.toJSONString(error);
        currentContext.setResponseBody(jsonString);
        return null;
    }
}
