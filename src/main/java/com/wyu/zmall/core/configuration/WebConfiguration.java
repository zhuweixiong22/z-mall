package com.wyu.zmall.core.configuration;

import com.wyu.zmall.core.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zwx
 * @date 2022-07-12 1:29
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 注册拦截器，可以注册多个
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor());
    }
}
