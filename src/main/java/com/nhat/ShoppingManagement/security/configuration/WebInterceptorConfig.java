package com.nhat.ShoppingManagement.security.configuration;

import com.nhat.ShoppingManagement.security.interceptor.UnverifiedUserInterceptor;
import com.nhat.ShoppingManagement.security.interceptor.UserIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserIdInterceptor()).addPathPatterns("/api/testapi/**");
        registry.addInterceptor(new UserIdInterceptor()).addPathPatterns("/api/testUnverified");
    }
}
