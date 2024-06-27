package org.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.test.interceptor.AdminInterceptor;
import org.test.interceptor.UserInterceptor;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    AdminInterceptor adminInterceptor;
    @Resource
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");

        registry.addInterceptor(userInterceptor).addPathPatterns("/user/**");

    }
}
