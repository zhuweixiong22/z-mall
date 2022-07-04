package com.wyu.zmall.core;

/**
 * @author zwx
 * @date 2022-06-27 17:37
 */
public class UnifyResponse {
    private Integer code;
    private String msg;
    private String request;

    public UnifyResponse(Integer code, String msg, String request) {
        this.code = code;
        this.msg = msg;
        this.request = request;
    }

    public static UnifyResponse error(Integer code, String msg, String request) {
        return new UnifyResponse(code, msg, request);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
