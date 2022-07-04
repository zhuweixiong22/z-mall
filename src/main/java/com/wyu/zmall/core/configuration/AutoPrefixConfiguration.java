package com.wyu.zmall.core.configuration;

import com.wyu.zmall.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Spring的一种发现机制：通过@Component + 实现接口的形式去发现类
 * TODO @Configuration注解一般是配合@Bean注解使用？
 * @author zwx
 * @date 2022-06-27 19:29
 */
@Component
public class AutoPrefixConfiguration implements WebMvcRegistrations {

    /**
     * 修改为获取我们自定义的RequestMappingHandlerMapping类
     *
     * @return
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixUrlMapping();
    }
}
