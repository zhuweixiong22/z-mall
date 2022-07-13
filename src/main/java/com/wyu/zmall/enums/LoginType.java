package com.wyu.zmall.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zwx
 * @date 2022-07-11 17:31
 */
@Getter
public enum LoginType {
    USER_WX(0, "微信登录"),

    USER_EMAIL(1, "邮箱登录"),

    ;

    private Integer code;

    private String desc;

    LoginType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static LoginType toType(Integer code) {
        return Stream.of(values())
                .filter(loginType-> Objects.equals(loginType.getCode(), code))
                .findAny()
                .orElse(null);
    }

}
