package com.ye186.thirdparty.myhelper.core.validator;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Set;

/**
 * @author ye17186
 * @date 2022-10-17
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    // 每位加权因子
    private static final int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final Set<String> provinceSet = Sets.newHashSet("11", "12", "13", "14", "15", "21", "22",
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

        boolean result;
        if (input.length() == 15) {
            input = card15To18(input);
        }
        result = validIdCardFor18(input);
        if (result) {
            String province = input.substring(0, 2);
            String birthday = input.substring(6, 14);
            if (!provinceSet.contains(province)) {
                result = false;
            } else {
                try {
                    LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd").withResolverStyle(ResolverStyle.STRICT));
                } catch (Exception e) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * 15位身份证转18位身份证
     *
     * @param idCard 身份证号
     */
    @Nullable
    private String card15To18(String idCard) {

        String idCard18 = null;
        if (idCard.length() == 15 && StringUtils.isNumeric(idCard)) {
            String idCard17 = idCard.substring(0, 6) + "19" + idCard.substring(6);
            char[] c = idCard17.toCharArray();
            int[] bits = char2int(c);
            int sum17 = getPowerSum(bits);
            String checkCode = getCheckCodeBySum(sum17);
            if (StringUtils.isNotEmpty(checkCode)) {
                idCard18 = idCard17 + checkCode;
            }
        }
        return idCard18;
    }

    /**
     * 校验18位的身份证号
     *
     * @param idCard 身份证号
     */
    private boolean validIdCardFor18(String idCard) {

        if (idCard == null || idCard.length() != 18) {
            return false;
        }

        String idCard17 = idCard.substring(0, 17); // 获取前17位
        String checkBit = idCard.substring(17, 18); // 获取第18位，校验位

        boolean result = false;
        if (StringUtils.isNumeric(idCard17)) {
            char[] c = idCard17.toCharArray();
            int[] bits = char2int(c);
            int sum17 = getPowerSum(bits);
            String checkCode = getCheckCodeBySum(sum17);
            result = checkBit.equalsIgnoreCase(checkCode);
        }
        return result;
    }

    public int[] char2int(char[] charArr) {

        int[] intArr = new int[charArr.length];
        for (int i = 0; i < charArr.length; i++) {
            intArr[i] = Integer.parseInt(String.valueOf(charArr[i]));
        }
        return intArr;
    }

    private String getCheckCodeBySum(int sum17) {

        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    private int getPowerSum(int[] bits) {

        int sum = 0;

        if (power.length != bits.length) {
            return sum;
        }

        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bits[i] * power[j];
                }
            }
        }
        return sum;
    }
}
