package com.wyu.zmall.enums;

import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zwx
 * @date 2022-07-13 21:17
 */
@Getter
public enum CouponStatus {
    AVAILABLE(1, "未使用"),

    USED(2, "已使用"),

    EXPIRED(3, "已过期"),

    ;

    private Integer code;

    private String desc;

    CouponStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // TODO 枚举值向枚举类型转换
    public static CouponStatus toType(Integer code) {
        return Stream.of(CouponStatus.values())// 获取所有的枚举值
                .filter(status -> Objects.equals(status.getCode(), code)) // 调用过滤器 返回一个布尔值
                .findAny() // 如果为true则返回该枚举
                .orElse(null);
    }
}
