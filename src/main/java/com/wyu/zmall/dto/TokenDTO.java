package com.wyu.zmall.dto;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * @author zwx
 * @date 2022-07-12 16:10
 */
@Data
public class TokenDTO {
    @NotBlank(message = "token不允许为空")
    private String token;
}
