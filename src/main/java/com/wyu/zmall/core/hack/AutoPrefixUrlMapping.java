package com.wyu.zmall.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * RequestMappingHandlerMapping类是Spring处理@Controller的路由
 *
 * @author zwx
 * @date 2022-06-27 19:22
 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Value("${z-mall.api-package}")
    private String apiPackagePath;

    /**
     * 重写该类 修改请求路由信息
     * SpringBoot启动时就会进入该方法，生成路由信息
     *
     * @param method
     * @param handlerType
     * @return
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo requestMappingInfo = super.getMappingForMethod(method, handlerType);
        if (requestMappingInfo != null) {
            String prefix = getPrefix(handlerType);
            // RequestMappingInfo.paths(prefix).build()生成的是前缀的一个路由 再combine合并上原来的路由就得到我们需要的自定义路由
            // TODO 2.6版本更改了路由配置 这里有坑
            return RequestMappingInfo.paths(prefix).build().combine(requestMappingInfo);
        }
        return requestMappingInfo;
    }

    /**
     * @param handlerType
     * @return
     */
    private String getPrefix(Class<?> handlerType) {
        // 获取到控制器所在的全路径包名：com.wyu.zmall.api.v1
        String packageName = handlerType.getPackage().getName();
        // 将packageName中com.wyu.zmall.api替换为空 --> .v1
        String dotPath = packageName.replaceAll(this.apiPackagePath, "");
        // 返回我们需要的路由前缀 /v1
        return dotPath.replace(".", "/");
    }
}
