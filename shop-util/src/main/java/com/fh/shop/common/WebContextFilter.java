package com.fh.shop.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WebContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强转获取HTTPServletRequest
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        try {
            //给ThreadLoad中存入request
            WebContext.setRequest(request);
            //继续执行后续代码
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            //用完之后删除ThreadLoad中的request
            WebContext.remove();
        }
    }

    @Override
    public void destroy() {

    }
}
