package io.github.ye17186.myhelper.core.utils;


import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ye17186
 * @since 2023-01-02
 */
public class IdUtils {

    private static final int timestampLen = 13;
    private static final AtomicInteger seq = new AtomicInteger(0);

    /**
     * 生成一个UUID
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String timestampId(int len) {

        String suffix = "";
        if (len > timestampLen) {
            int suffixLen = len - timestampLen;
            int suffixSeq = Double.valueOf(seq.incrementAndGet() % Math.pow(10, suffixLen)).intValue();
            suffix = StringUtils.leftPad(String.valueOf(suffixSeq), len - timestampLen, '0');
        }
        return System.currentTimeMillis() + suffix;
    }
}
