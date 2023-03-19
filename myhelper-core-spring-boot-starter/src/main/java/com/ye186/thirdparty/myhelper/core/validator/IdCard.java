package com.ye186.thirdparty.myhelper.core.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号参数校验
 *
 * @author ye17186
 * @date 2020-11-19
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IdCardValidator.class})
public @interface IdCard {

    String message() default "{com.ye186.thirdparty.fhm.common.valid.IdCard.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 是否忽略空值
     */
    boolean required() default false;
}
