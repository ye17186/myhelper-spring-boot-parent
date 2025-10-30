package io.github.ye17186.myhelper.core.utils;

/**
 * 数据脱敏工具类
 *
 * @author ye17186
 * @date 2023/12/27
 */
public class DesensitizedUtils {

    /**
     * 脱敏（默认占位符*）
     *
     * @param source 原字符串
     * @param start  起始位置
     * @param end    结束为止
     * @return 脱敏后的字符串
     */
    public static String desensitized(String source, int start, int end) {

        return desensitized(source, start, end, '*');
    }

    /**
     * 脱敏
     *
     * @param source  原字符串
     * @param start   起始位置
     * @param end     结束为止
     * @param replace 指定占位符
     * @return 脱敏后的字符串
     */
    public static String desensitized(String source, int start, int end, char replace) {

        if (StringUtils.isEmpty(source)) {
            return source;
        }

        char[] arr = source.toCharArray();
        for (int i = start; i < end; i++) {
            arr[i] = replace;
        }
        return new String(arr);
    }

    /**
     * 脱敏电子邮箱
     *
     * @param email 邮箱地址
     */
    public static String email(String email) {

        int index = email.indexOf(StringPool.AT);
        return index < 0 ? email : desensitized(email, 0, index);
    }

    /**
     * 脱敏手机号
     *
     * @param mobile 手机号
     */
    public static String mobile(String mobile) {

        return desensitized(mobile, 3, mobile.length() - 4);
    }

    /**
     * 脱敏身份证号
     *
     * @param idCard 身份证号
     */
    public static String idCard(String idCard) {

        return desensitized(idCard, 2, idCard.length() - 2);
    }
}
