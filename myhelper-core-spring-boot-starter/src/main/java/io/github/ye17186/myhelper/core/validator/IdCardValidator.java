package io.github.ye17186.myhelper.core.validator;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author ye17186
 * @since 2022-10-17
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    // 权重系数数组，用于计算校验码
    private static final int[] COEFFICIENTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    // 校验码对照表
    private static final char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    // 正则表达式预编译
    private static final Pattern ID_PATTERN = Pattern.compile("^[1-9]\\d{5}(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$");
    private static final Set<String> PROVINCE_SET = Sets.newHashSet("11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91");

    private IdCard annotation;

    @Override
    public void initialize(IdCard annotation) {

        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = true;
        boolean required = annotation.required();

        if (StringUtils.isNotEmpty(input) || required) {
            return validIdCard(input);
        }
        return result;
    }

    private boolean validIdCard(String input) {

        // 校验位数和省份
        if (StringUtils.length(input) != 18 || !isValidProvince(input)) {
            return false;
        }

        // 1. 正则表达式验证格式
        if (!ID_PATTERN.matcher(input).matches()) {
            return false;
        }

        // 2. 验证日期合法性
        if (!isValidDate(input)) {
            return false;
        }

        // 3. 验证校验码
        return isValidCheckCode(input);
    }

    /**
     * 校验身份证校验码
     *
     * @param input 身份证号
     * @return 是否为合法的身份证校验码
     */
    private boolean isValidCheckCode(String input) {

        char[] chars = input.toCharArray();
        int sum = 0;

        // 计算前17位与权重的乘积和
        for (int i = 0; i < 17; i++) {
            sum += (chars[i] - '0') * COEFFICIENTS[i];
        }

        // 计算校验码
        char actualCode = Character.toUpperCase(chars[17]);
        char expectedCode = CHECK_CODES[sum % 11];
        return actualCode == expectedCode;
    }

    /**
     * 校验是否为身份证合法日期
     *
     * @param input 身份证号
     * @return 是否为合法的身份证出生日期
     */
    private boolean isValidDate(String input) {

        try {
            LocalDate.parse(input.substring(6, 14), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * 校验是否为身份证合法的省份
     *
     * @param input 待校验的身份证号
     * @return 是否为合法的身份证省份
     */
    private boolean isValidProvince(String input) {

        return PROVINCE_SET.contains(input.substring(0, 2));
    }
}
