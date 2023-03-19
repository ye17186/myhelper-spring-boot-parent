package com.ye186.thirdparty.myhelper.token.utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author ye17186
 * @date 2023-03-16
 */
public class LoginTypeUtils {

    public static final String DEFAULT_TYPE = "default";
    private static final String SEPARATOR = ":";

    public static String encode(String loginType, String loginId) {

        return loginType + SEPARATOR + loginId;
    }

    public static Pair<String, String> decode(String loginId) {

        String[] arr = loginId.split(SEPARATOR);
        return Pair.of(arr[0], arr[1]);
    }
}
