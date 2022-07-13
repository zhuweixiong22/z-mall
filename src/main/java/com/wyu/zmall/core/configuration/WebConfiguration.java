package com.wyu.zmall.core.configuration;

import com.wyu.zmall.core.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zwx
 * @date 2022-07-12 1:29
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public PermissionInterceptor getPermissionInterceptor(){
        return new PermissionInterceptor();
    }
    /**
     * 注册拦截器，可以注册多个
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new PermissionInterceptor());
        // TODO 为什么这里不能直接new
        //  因为PermissionInterceptor里我们需要依赖注入一个UserService 如果在PermissionInterceptor再打上@Component注解会发生什么事呢
        //  这里的方法先执行，加入到容器的是new的这个对象，PermissionInterceptor那里又注入了一次，导致两次注入的不是同一个对象会出现问题，UserService注入不成功
        registry.addInterceptor(this.getPermissionInterceptor());
    }
}
