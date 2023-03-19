package com.ye186.thirdparty.myhelper.core.utils;

import org.springframework.util.DigestUtils;

/**
 * @author ye17186
 * @date 2023-03-01
 */
public class PasswordEncoder {

    /**
     * 密码加密
     *
     * @param rawPassword 明文密码
     * @param salt        盐值
     * @return 密文密码
     */
    public static String encode(String rawPassword, String salt) {

        return DigestUtils.md5DigestAsHex((rawPassword + salt).getBytes());
    }

    /**
     * 密码匹配
     *
     * @param rawPassword 原文密码
     * @param salt        盐值
     * @param password    密码密码
     */
    public static boolean match(String rawPassword, String salt, String password) {

        return password.equals(encode(rawPassword, salt));
    }
}
