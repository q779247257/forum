package com.xuan.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/22
 * @描述：拦截器的实现配置
 */

@Configuration
//@EnableWebMvc
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired private SessionInterceptor sessionInterceptor;


    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**");

    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    //addPathPatterns 哪些路径需要进行处理
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                //配置哪些路径不需要处理
                .excludePathPatterns("/" /* 首页 */,
                        "/hello" /* 首页 */,
                        "/callback"/* github回调地址 */,
                        "/favicon.ico",
                        "/question/**",/** 读取文章不需要登录 */
                        "/css/**");

    }
}
