package io.github.ye17186.myhelper.core.utils;


import java.util.UUID;

/**
 * @author ye17186
 * @since 2023-01-02
 */
public class IdUtils {

    /**
     * 生成一个UUID
     * @return
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
