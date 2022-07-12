package com.wyu.zmall.core.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author zwx
 * @date 2022-07-11 17:21
 */
@Documented // 可以将注解里的注释加入到文档里
@Retention(RetentionPolicy.RUNTIME) // 指定注解保留到运行阶段
@Target({ElementType.FIELD}) // 指定注解应用范围
@Constraint(validatedBy = TokenPasswordValidator.class) // 传入关联类(可以用花括号传入多个关联类)
public @interface TokenPassword {

    String message() default "password is not valid";

    // 注解里只能使用基本类型
    int min() default 6;

    int max() default 32;

    // 两个模板方法 写自定义注解的规范
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 关联类 SpringBoot的编程模型
}
