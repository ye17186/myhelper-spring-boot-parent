package com.ye186.thirdparty.myhelper.core.utils;


import java.util.UUID;

/**
 * @author ye17186
 * @date 2023-01-02
 */
public class IdUtils {

    /**
     * 生成一个UUID
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
