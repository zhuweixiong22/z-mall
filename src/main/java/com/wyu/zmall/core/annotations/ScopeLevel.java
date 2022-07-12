package com.wyu.zmall.core.annotations;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author zwx
 * @date 2022-07-11 23:40
 */
@Documented // 可以将注解里的注释加入到文档里
@Retention(RetentionPolicy.RUNTIME) // 指定注解保留到运行阶段
@Target({ElementType.METHOD}) // 指定注解应用范围
public @interface ScopeLevel {
    int value() default 4;
}
