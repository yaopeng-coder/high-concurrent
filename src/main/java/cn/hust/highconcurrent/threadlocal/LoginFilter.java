package cn.hust.highconcurrent.threadlocal;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 18:38
 **/
@Slf4j
@Configuration
@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest)request;
        RequestHolder.set(Thread.currentThread().getId());
        log.info("filter,{}",Thread.currentThread().getId());
        chain.doFilter(request,response);


    }

    @Override
    public void destroy() {

    }
}
