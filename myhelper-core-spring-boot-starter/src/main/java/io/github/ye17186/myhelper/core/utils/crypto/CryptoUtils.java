package io.github.ye17186.myhelper.core.utils.crypto;

/**
 * 加解密工具类（目前提供了RSA、AES两种加密方式）
 *
 * @author ye17186
 * @date 2023/10/24
 */
public class CryptoUtils {

    private CryptoUtils() {
    }

    /**
     * AES加解密算法实例
     */
    public static final AES AES = io.github.ye17186.myhelper.core.utils.crypto.AES.getInstance();

    /**
     * RSA加解密算法实例
     */
    public static final RAS RAS = io.github.ye17186.myhelper.core.utils.crypto.RAS.getInstance();
}
