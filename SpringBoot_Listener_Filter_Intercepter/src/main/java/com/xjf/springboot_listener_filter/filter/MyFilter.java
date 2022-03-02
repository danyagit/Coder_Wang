package com.xjf.springboot_listener_filter.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Log4j2
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器");
    }
  
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        String requestUri = request.getRequestURI();
        log.info("请求地址是："+requestUri);
        if (requestUri.contains("/addSession")
            || requestUri.contains("/removeSession")
            || requestUri.contains("/online")
            || requestUri.contains("/favicon.ico")) {
            filterChain.doFilter(servletRequest, response);
        } else {
            wrapper.sendRedirect("/online");
        }
    }
  
    @Override
    public void destroy() {
        //在服务关闭时销毁
        log.info("销毁过滤器");
    }
}