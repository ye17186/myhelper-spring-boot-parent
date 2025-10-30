package io.github.ye17186.myhelper.token.utils;

import cn.dev33.satoken.secure.SaSecureUtil;

/**
 * @author ye17186
 * @since 2023-03-03
 * @deprecated token模块不在提供该功能，改有core模块提供
 */
public class EncryptUtils {

    public static String md5(String text) {

        return SaSecureUtil.md5(text);
    }

    public static String md5(String text, String slat) {

        return SaSecureUtil.md5BySalt(text, slat);
    }

    public static String sha1(String text) {

        return SaSecureUtil.sha1(text);
    }

    public static String sha256(String text) {

        return SaSecureUtil.sha256(text);
    }

    public static String aes(String key, String text) {

        return SaSecureUtil.aesEncrypt(key, text);
    }
}
