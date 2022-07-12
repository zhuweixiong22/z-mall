package com.wyu.zmall.dto;

import com.wyu.zmall.enums.LoginType;
import com.wyu.zmall.core.annotations.TokenPassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zwx
 * @date 2022-07-11 17:12
 */
@Data
public class UserDTO {

    @NotBlank(message = "{token.account}")
    private String account;

    @TokenPassword(message = "{token.password}")
    private String password;

    private LoginType loginType;
}
