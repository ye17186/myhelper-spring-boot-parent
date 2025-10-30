package io.github.ye17186.myhelper.core.utils.crypto;

import org.springframework.util.DigestUtils;

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

    public String digest(String raw) {

        return DigestUtils.md5DigestAsHex(raw.getBytes());
    }
}
