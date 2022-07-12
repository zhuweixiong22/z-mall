package com.wyu.zmall.service;

/**
 * @author zwx
 * @date 2022-07-11 20:27
 */
public interface WxAuthenticationService {

    String code2Session(String code);
}
