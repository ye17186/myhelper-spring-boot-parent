package io.github.ye17186.myhelper.core.utils.crypto;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ye17186
 * @date 2023/11/21
 */
public class AES {

    private static final String AES_ALGORITHM = "AES"; // 默认 AES/ECB/PKCS5Padding
    private static final String KEY = "yJ6PqL5RFDbfaQ3yBM25amp/PqWNfK+hZ+DwJFXinbA=";

    private AES() {}

    protected static AES getInstance() {
        return new AES();
    }

    private static SecretKey getKey(String key) {

        byte[] bytes = Base64Utils.decodeFromString(key);
        return new SecretKeySpec(bytes, "AES");
    }

    public String encrypt(String raw) {

        return encrypt(KEY, raw);
    }

    public String encrypt(String key, String raw) {

        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
            byte[] result = cipher.doFinal(raw.getBytes());
            return Base64Utils.encodeToString(result);
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e);
        }
    }

    public String decrypt(String raw) {

        return decrypt(KEY, raw);
    }

    public String decrypt(String key, String raw) {

        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));
            byte[] result = cipher.doFinal(Base64Utils.decodeFromString(raw));
            return new String(result);
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e);
        }
    }
}
