package com.wyu.zmall.core.annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zwx
 * @date 2022-07-11 17:23
 */
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword, String> {

    private int min;

    private int max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        // 获取注解中的参数
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 小程序登录方式中不需要密码
        if (StringUtils.isEmpty(s)) {
            return true;
        }
        return s.length() >= this.min && s.length() <= this.max;
    }
}
