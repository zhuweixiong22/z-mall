package com.wyu.zmall.enums;

/**
 * @author zwx
 * @date 2022-06-27 19:07
 */
public enum ResponseCode {
    ERROR(10000, "服务端异常"),

    SUCCESS(0, "成功"),

    PARAM_ERROR(10001, "参数错误"),

    SPU_NOT_FOUND(30003, "商品资源不存在"),

    BANNER_NOT_FOUND(30005, "Banner类资源不存在"),

    ;

    Integer code;

    String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
