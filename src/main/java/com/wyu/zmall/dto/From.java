package com.wyu.zmall.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * @author zwx
 * @date 2022-06-28 0:54
 */
public class From {
    @Length(min = 2, max = 5, message = "name长度要在2-5之间")
    private String name;

    @Length(min = 2,message = "密码长度至少为2位")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
