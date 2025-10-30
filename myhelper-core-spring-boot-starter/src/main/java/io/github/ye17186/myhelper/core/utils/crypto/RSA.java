package io.github.ye17186.myhelper.core.utils.crypto;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author ye17186
 * @date 2023/11/21
 */
public class RSA {

    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String DEFAULT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4n9pKnjY7VGy8DF9oE/QNSd8C5lpFE5wmQX1DhNHHG11zoEsJ+hQ0Y+76cIHEpNEaYpXFV/wor46C+sJhuiP4LchnWaN6d8LdgfM8LkQk8scRHCZgpKhlHfLz9t4bbkQba6OQOMCKgtsVjlyb26fvdOHSn/kxa6eCOxmZuB2zNtEQOeiSCxiOPFW+CjV2Hmjx0MghSsEILut8N4PIXNwYXQveujTlBOpD7/UZkuhcOjTsigHKdFUXVYABsJbRaveyzINpDeAo48xmsdq6PmMft7Ke+0x3yEBCpl0DaXvJcwuYXtsNByjzc90exkOwqZvmS/G6hzVZI7ReV34k6TyxwIDAQAB";
    private static final String DEFAULT_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDif2kqeNjtUbLwMX2gT9A1J3wLmWkUTnCZBfUOE0ccbXXOgSwn6FDRj7vpwgcSk0RpilcVX/CivjoL6wmG6I/gtyGdZo3p3wt2B8zwuRCTyxxEcJmCkqGUd8vP23htuRBtro5A4wIqC2xWOXJvbp+904dKf+TFrp4I7GZm4HbM20RA56JILGI48Vb4KNXYeaPHQyCFKwQgu63w3g8hc3BhdC966NOUE6kPv9RmS6Fw6NOyKAcp0VRdVgAGwltFq97LMg2kN4CjjzGax2ro+Yx+3sp77THfIQEKmXQNpe8lzC5he2w0HKPNz3R7GQ7Cpm+ZL8bqHNVkjtF5XfiTpPLHAgMBAAECggEBAIJ0Zis7TZ5q5whnSPcz4TUsV8sKgjZa84l/n+SKf/PErAzT443cxefdxJrf0VkGj2nGb1/1UVE7BY3lwyf7/Rqt/+N7tRNWD3DdD1I7FWw6mT5mKFTHhd9QnIhM3XE9HVQ/LNn8pjt3Nt/22I2MBt+73Pmw7iBZ8dYr6tMoR+S29HDDtOKp22k1g3LEQSvcX0i61e7cpGCrkHxUxH221jro4EBu+YiG+XtxFB+FmBAesQVuKoOqIoMiV0Wih7ibKqsJf2p3yyDV2Vg8mWi0mmQAoR5r63cx/IxzfDp+11r+IYbUH02K93gjDDvawQ4Ux2km9nodXv3OJOwUkn8xVpkCgYEA+NmQJWCosGyieXhVgIXgmcjCE+nVuxqH1Vf883yKtjl6+zRR+vVHqCmbKxbylxXvNYoXTvea/muGBdUacyaZL2/ZvFmN+4wpiI9LybTa7n6NhDCWCgsrJtYMEsTSSzcKixKeU2h6EfhtMJTo4oF3shnLGNruI/sI51B3PDbE/bUCgYEA6QFvOi45qyT9benKGL0A7nOnzcmFe7ewEGZwncDlav3bRi9VDeO0jt7Xi9aOPAWZItvm+Ez7sMtcB5F3a3lh/6JIlkdBlqqDJ21Ap+byerQg3uOMbBFQYxAgPVHW6sWPK2jQX3kP+guDpMmU8LyoHv1B/9KQ4czwgJwr6j6dXAsCgYAulDKZ/HS2yS7EQ9TlQKngvyYsqgk6g2XHdje/r6EbKLkvSgTKT50BCgCkWSBBTXa/88AXJNubIa8tak0QHrpvmaUMz0p3Oi4QBJpv/+021PNSOfHroVmLZGdQIA0ozP2pOGvwOs8WjASMImNQ+mDYMmHEnj4S+AsbmQEogSvwPQKBgQDnx22FSoZDIMCYnX9TrG9Iw/OgWV5W+xpAtiZARqnwDCYbYuoQrvnlRPbVO9CxFECI1IalaK7V5vTc8m8AkfZR/HSISMmNvoBIr/0pCCsctCIelewRdAerA1eOp+JKF8ubOuBMmHi0GtARFBT8hC4Y5YTJngRRGTQEO1XT5gVSkQKBgQDcIuBbxDplmlMsVuC2vSC2TCal9Iv8QsxIStqi6JLUz8ipt+ixymNtFdJWHQY4mSeRBoVclelyVvohoUBSpBD/z0dTdNlz7VWtfvuGLccj/g+KO36dKD4uq6cDwK4ivxlZyieg5h6HDdmozgCsH0GMosHXYMwyjWWJAe3YywFV5Q==";

    private RSA() {
    }

    protected static RSA getInstance() {
        return new RSA();
    }

    /**
     * 获取公钥
     *
     * @param key 秘钥字符串（Base64编码）
     * @return 公钥
     */
    private static PublicKey getPublicKey(String key) throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Utils.decodeFromString(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 获取私钥
     *
     * @param key 秘钥字符串（Base64编码）
     * @return 秘钥
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public String encrypt(String raw) {

        return encrypt(DEFAULT_PUBLIC_KEY, raw);
    }

    /**
     * 生成RSA秘钥对
     *
     * @return RSA秘钥对
     */
    public String[] generate() throws NoSuchAlgorithmException {

        return generate(1024);
    }

    /**
     * 生成RSA秘钥对
     *
     * @param keySize key大小
     * @return RSA秘钥对
     */
    public String[] generate(int keySize) throws NoSuchAlgorithmException {

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keySize, new SecureRandom());

        KeyPair keyPair = generator.generateKeyPair();
        byte[] pub = keyPair.getPublic().getEncoded();
        byte[] pri = keyPair.getPrivate().getEncoded();


        return new String[]{Base64Utils.encodeToString(pub), Base64Utils.encodeToString(pri)};
    }

    public String encrypt(String publicKey, String raw) {

        try {
            Key key = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryption = cipher.doFinal(raw.getBytes());
            return Base64Utils.encodeToString(encryption);
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e);
        }
    }

    public String decrypt(String raw) {

        return decrypt(DEFAULT_PRIVATE_KEY, raw);
    }

    public String decrypt(String privateKey, String raw) {

        try {
            Key key = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryption = cipher.doFinal(Base64Utils.decodeFromString(raw));
            return new String(decryption);
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e);
        }
    }
}
