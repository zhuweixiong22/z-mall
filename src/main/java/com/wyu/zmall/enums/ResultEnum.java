package com.wyu.zmall.enums;

/**
 * @author zwx
 * @date 2022-06-27 19:07
 */
public enum ResultEnum {
    ERROR(10000, "服务端异常"),

    SUCCESS(0, "成功"),

    PARAM_ERROR(10001, "参数错误"),

    LOGIN_NOT_FOUND(10003, "没有找到合适的登陆处理方法"),

    UNAUTHORIZED(10004, "用户未登录"),

    FORBIDDEN(10005, "权限不足"),



    GET_WX_OPEN_ID_ERROR(20004, "获取用户wx openid失败"),

    TOKEN_EXPIRED(20005, "token已过期"),

    TOKEN_ERROR(20006, "非法token"),

    SPU_NOT_FOUND(30003, "商品资源不存在"),


    BANNER_NOT_FOUND(30005, "Banner类资源不存在"),

    ;

    Integer code;

    String desc;

    ResultEnum(Integer code, String desc) {
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
