package com.wyu.zmall.exception.http;

/**
 * @author zwx
 * @date 2022-06-27 17:27
 */
public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode = 500;

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public HttpException(){

    }

    public HttpException(Integer code, String desc) {
        super(desc);
        this.code = code;
    }

    public HttpException(Integer code, String desc, Integer httpStatusCode) {
        super(desc);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }
}
