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


    COUPON_NOT_FOUND(40004, "未找到优惠券"),

    COUPON_EXPIRED(40005, "优惠券已过期"),

    COUPON_COLLECTED(40006, "您已经领取了这张优惠券"),
    COUPON_ACTIVITY_NOT_FOUND(40010, "未找到优惠券对应的活动"),

    COUPON_COLLECT_TIME_ERROR(40013, "优惠券领取时间错误")


    /**
     * lin.codes[40000] = 活动与优惠券通用错误
     * lin.codes[40001] = 未找到首页优惠券活动
     * lin.codes[40002] = 未找到商品可用优惠券
     * lin.codes[40003] = 未找到活动封面
     * lin.codes[40004] = 未找到优惠券
     * lin.codes[40005] = 优惠券已过期
     * lin.codes[40006] = 您已经领取了这张优惠券
     * lin.codes[40007] = 优惠券已被使用，不能重复使用
     * lin.codes[40008] = 优惠券不满足使用条件
     * lin.codes[40009] = 不支持的优惠券类型
     * lin.codes[40010] = 未找到优惠券对应的活动
     * lin.codes[40011] = 优惠券只存在可使用/已使用/已过期的情况
     * lin.codes[40012] = 优惠券核销失败
     */

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
