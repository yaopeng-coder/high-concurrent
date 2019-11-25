package cn.hust.highconcurrent.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 18:43
 **/
@Slf4j
public class LoginIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("prehandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion,{}",RequestHolder.get());
        RequestHolder.remove();
        log.info("afterCompletion,{}",RequestHolder.get());
        return;
    }
}
