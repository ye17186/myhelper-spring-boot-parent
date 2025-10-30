package io.github.ye17186.myhelper.core.utils;

import org.springframework.util.DigestUtils;

/**
 * 密码工具类
 *
 * @author ye17186
 * @since 2023-03-01
 * @deprecated 使用 PasswordUtils代替
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

        return DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(rawPassword.getBytes()) + DigestUtils.md5DigestAsHex(salt.getBytes())).getBytes());
    }

    /**
     * 密码匹配
     *
     * @param rawPassword 原文密码
     * @param salt        盐值
     * @param password    密码密码
     * @return 密码是否匹配
     */
    public static boolean match(String rawPassword, String salt, String password) {

        return password.equals(encode(rawPassword, salt));
    }
}
