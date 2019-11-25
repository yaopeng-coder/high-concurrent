package cn.hust.highconcurrent.threadlocal;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: high-concurrent
 * @author: yaopeng
 * @create: 2019-11-25 18:48
 **/
@Configuration
public class Config extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginIntercepter())
               .addPathPatterns("/threadlocal/**");
    }


    /**
     * filter中url映射只能用/*,不能用/**
     * 可以在filter上加@configuration和@webfilter替代下面的注册
     * @return
     */
//    @Bean
//    public FilterRegistrationBean registrationBean(){
//        FilterRegistrationBean registrationBean  = new FilterRegistrationBean();
//        registrationBean.setFilter(new LoginFilter());
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
}
