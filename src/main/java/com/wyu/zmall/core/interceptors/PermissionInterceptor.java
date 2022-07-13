package com.wyu.zmall.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.wyu.zmall.core.annotations.ScopeLevel;
import com.wyu.zmall.enums.ResultEnum;
import com.wyu.zmall.exception.http.HttpException;
import com.wyu.zmall.util.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zwx
 * @date 2022-07-11 23:32
 */
public class PermissionInterceptor implements HandlerInterceptor {

    /**
     * 拦截器逻辑：
     * 1、获取到请求token
     * 2、验证token
     * 3、scope 访问的是否是公共API
     * 4、读取要访问API的@ScopeLevel的level
     * 5、比较等级 scope ？ level
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ScopeLevel scopeLevel = this.getScopeLevel(handler);
        // 没有@ScopeLevel 公开API直接返回
        if (scopeLevel == null) {
            return true;
        }
        String bearerToken = request.getHeader("Authorization");
        // token为空并且访问的不是公共api，说明未登录
        if (!StringUtils.hasText(bearerToken)) {
            throw new HttpException(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getDesc(), HttpStatus.UNAUTHORIZED.value());
        }
        // token规范形式：Authorization: Bearer <token>
        if (!bearerToken.startsWith("Bearer")) {
            throw new HttpException(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getDesc(), HttpStatus.BAD_REQUEST.value());
        }
        String[] tokens = bearerToken.split(" ");
        // 避免数组越界
        if (tokens.length != 2) {
            throw new HttpException(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getDesc(), HttpStatus.BAD_REQUEST.value());
        }
        // 校验
        Map<String, Claim> claims = TokenUtil.verifyToken(tokens[1]);
        return this.hasPermission(scopeLevel, claims);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    private ScopeLevel getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            // 这里可能为null，在调用getScopeLevel的方法中处理
            return scopeLevel;
        }
        throw new HttpException(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getDesc());
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> claims) {
        int level = scopeLevel.value();
        int scope = claims.get("scope").asInt();
        // 权限等级不够
        if (level > scope) {
            throw new HttpException(ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getDesc(), HttpStatus.FORBIDDEN.value());
        }
        return true;
    }

    private void setToThreadLocal(Map<String, Claim> claims) {
        Long uid = claims.get("uid").asLong();
        Integer scope = claims.get("scope").asInt();

    }
}
