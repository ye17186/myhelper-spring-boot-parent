package com.ye186.thirdparty.myhelper.core.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举参数校验
 *
 * @author ye17186
 * @date 2020-11-19
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {EnumValidator.class})
public @interface IsEnum {

    String message() default "{com.ye186.thirdparty.fhm.common.valid.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 目标枚举类
     */
    Class<?> target() default Class.class;

    /**
     * 校验域
     */
    String field() default "code";

    /**
     * 是否忽略空值
     */
    boolean required() default false;
}