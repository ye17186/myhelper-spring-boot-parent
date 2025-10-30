package io.github.ye17186.myhelper.core.utils;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.util.DigestUtils;

/**
 * 密码处理工具类
 * @author ye17186
 * @date 2024/5/26
 */
public class PasswordUtils {

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
     * @return 密码是否匹配成功
     */
    public static boolean match(String rawPassword, String salt, String password) {

        return password.equals(encode(rawPassword, salt));
    }

    /**
     * 评估密码强度
     *
     * @param rawPassword 明文密码
     * @return 强度 0 - 4
     */
    public static int measure(String rawPassword) {

        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(rawPassword);
        return strength.getScore();
    }
}
