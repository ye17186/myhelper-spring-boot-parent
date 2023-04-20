package io.github.ye17186.myhelper.token.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 多账号体系下的登录Key
 *
 * @author yemeng20
 * @date 2023-04-20
 */
@Setter
@Getter
public class LoginKey implements Serializable {

    private static final String SEPARATOR = ":";
    private static final String DEFAULT_TYPE = "default";

    private String loginType = DEFAULT_TYPE;
    private String loginId;

    private LoginKey() {
    }

    public static LoginKey create(String loginId) {

        LoginKey key = new LoginKey();
        key.setLoginId(loginId);
        return key;
    }

    public static LoginKey create(String loginType, String loginId) {

        LoginKey key = new LoginKey();
        key.setLoginType(loginType);
        key.setLoginId(loginId);
        return key;
    }

    public String format() {

        return encode(loginType, loginId);
    }

    public static LoginKey decode(String saLoginId) {

        String[] arr = saLoginId.split(SEPARATOR);
        LoginKey key = new LoginKey();
        key.setLoginType(arr[0]);
        key.setLoginId(arr[1]);
        return key;
    }

    public static String encode(String loginId) {

        return encode(DEFAULT_TYPE, loginId);
    }

    public static String encode(String loginType, String loginId) {

        return loginType + SEPARATOR + loginId;
    }
}
