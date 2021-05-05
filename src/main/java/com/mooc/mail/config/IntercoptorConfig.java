package com.mooc.mail.config;

import com.mooc.mail.intercptor.UserLoginIntercptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class IntercoptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginIntercptor()).addPathPatterns("/**").excludePathPatterns("/user/login", "/user/register", "/categories");
    }
}
