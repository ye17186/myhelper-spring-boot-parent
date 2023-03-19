package io.github.ye17186.myhelper.core.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 枚举参数校验器
 *
 * @author ye17186
 * @since 2020-11-19
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private static final Pattern p = Pattern.compile("^1([3-9])\\d{9}$");

    private Mobile annotation;

    @Override
    public void initialize(Mobile annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = true;
        boolean required = annotation.required();

        if (StringUtils.isNotEmpty(input) || required) {
            Matcher m = p.matcher(input);
            result = m.matches();
        }
        return result;
    }
}
