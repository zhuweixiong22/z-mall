package com.wyu.zmall.exception.http;

/**
 * @author zwx
 * @date 2022-06-27 17:29
 */
public class NotFoundException extends HttpException{
    public NotFoundException(Integer code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}
