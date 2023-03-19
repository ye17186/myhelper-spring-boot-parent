package io.github.ye17186.myhelper.core.validator;

import io.github.ye17186.myhelper.core.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 枚举参数校验器
 *
 * @author ye17186
 * @since 2020-11-19
 */
public class EnumValidator implements ConstraintValidator<IsEnum, Object> {

    private IsEnum annotation;

    @Override
    public void initialize(IsEnum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Object input, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        Class<?> cls = annotation.target();
        boolean required = annotation.required();

        String inputStr = getInputStr(input);

        if (cls.isEnum() && (StringUtils.isNotEmpty(inputStr) || required)) {
            try {
                Field codeField = cls.getDeclaredField(annotation.field());
                codeField.setAccessible(true);
                Object[] objects = cls.getEnumConstants();
                for (Object obj : objects) {
                    Object fieldValue = codeField.get(obj);
                    if (StringUtils.isNotEmpty(inputStr) && inputStr.equals(fieldValue.toString())) {
                        result = true;
                        break;
                    }
                }
            } catch (Exception ignore) {
            }
        } else {
            result = true;
        }
        return result;
    }

    private String getInputStr(Object input) {
        return input == null ? null : input.toString();
    }

}
