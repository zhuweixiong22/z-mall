package com.wyu.zmall.core;

import com.wyu.zmall.bo.LocalUser;
import com.wyu.zmall.model.User;

/**
 * @author zwx
 * @date 2022-07-13 16:09
 */
public class LocalUserThreadHolder {
    private static final ThreadLocal<LocalUser> threadLocal = new ThreadLocal<>();

    public static void setLocalUser(User user, Integer scope) {
        LocalUser localUser = new LocalUser();
        localUser.setUser(user);
        localUser.setScope(scope);
        LocalUserThreadHolder.threadLocal.set(localUser);
    }

    public static User getLocalUser() {
        return LocalUserThreadHolder.threadLocal.get().getUser();
    }

    public static Integer getLocalUserScope() {
        return LocalUserThreadHolder.threadLocal.get().getScope();
    }

    public static Long getLocalUserId() {
        return LocalUserThreadHolder.threadLocal.get().getUser().getId();
    }

    public static void clear(){
        LocalUserThreadHolder.threadLocal.remove();
    }
}
