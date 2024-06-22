package com.hust.hungry.config;

import com.hust.hungry.interceptor.LoginInterceptor;
import com.hust.hungry.interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        registry.addInterceptor(createTestInterceptor())
                //排除在此拦截器外
                .excludePathPatterns("/user/login")
                //被拦截的规则
                .addPathPatterns("/**");						//添加需要拦截的路径

        //注册TestInterceptor拦截器
        registry.addInterceptor(createLoginInterceptor())
                //排除在此拦截器外
                .excludePathPatterns("/user/login")
                //被拦截的规则
                .addPathPatterns("/**");
    }

    @Bean
    public TestInterceptor createTestInterceptor(){
        return new TestInterceptor();
    }


    @Bean
    public LoginInterceptor createLoginInterceptor(){
        return new LoginInterceptor();
    }

}
