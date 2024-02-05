package io.github.ye17186.myhelper.core.utils.crypto;

import org.springframework.util.Base64Utils;

/**
 * @author ye17186
 * @date 2023/11/21
 */
public class MD5 {

    private MD5() {
    }

    protected static MD5 getInstance() {

        return new MD5();
    }

    public String encrypt(String raw) {

        return Base64Utils.encodeToString(raw.getBytes());
    }

    public String encrypt(String raw, String slat) {

        String s = Base64Utils.encodeToString(raw.getBytes()) + Base64Utils.encodeToString(slat.getBytes());
        return Base64Utils.encodeToString(s.getBytes());
    }
}
