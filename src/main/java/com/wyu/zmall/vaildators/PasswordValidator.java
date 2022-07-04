package com.wyu.zmall.vaildators;

import com.wyu.zmall.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zwx
 * @date 2022-06-27 23:36
 */
// 第一个类型：自定义注解的类型 第二个类型：自定义注解修饰的目标的类型（即PasswordEqual用在哪，用在类上就写类型，用在字段上就写字段类型）
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    private int min;

    private int max;

    /**
     * 如我们要写min、max等这一类注解是有参数的，则我们需要通过覆盖该方法拿到注解里的参数
     * @param constraintAnnotation
     */
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        // 获取注解中的参数
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {
        // TODO 可以写得更加严谨 加上判空
        return personDTO.getPassword1().equals(personDTO.getPassword2());
    }
}
