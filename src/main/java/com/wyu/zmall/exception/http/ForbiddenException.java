package com.wyu.zmall.exception.http;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author zwx
 * @date 2022-06-27 17:31
 */
public class ForbiddenException extends HttpException{
    public ForbiddenException(Integer code) {
        this.httpStatusCode = 403;
        this.code = code;
    }
}
