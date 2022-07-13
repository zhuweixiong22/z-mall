package com.wyu.zmall.core;

import com.wyu.zmall.enums.ResultEnum;
import lombok.Data;

/**
 * @author zwx
 * @date 2022-06-27 17:37
 */
@Data
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


    public static UnifyResponse success() {
        return new UnifyResponse(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), null);
    }
}
