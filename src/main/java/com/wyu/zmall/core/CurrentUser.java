package com.wyu.zmall.core;

import com.wyu.zmall.model.User;
import com.wyu.zmall.util.TokenUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zwx
 * @date 2022-07-13 16:09
 */
public class CurrentUser {
    private static User user;

    public static void setUser(User user) {
        CurrentUser.user = user;
    }

    public static User getUser() {
        return CurrentUser.user;
    }

    public static Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String token = requestAttributes.getRequest().getHeader("token");

        return null;
    }
}
