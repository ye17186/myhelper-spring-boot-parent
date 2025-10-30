package io.github.ye17186.myhelper.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ye17186
 * @since 2023-01-02
 */
public class IdUtils {

    private static final int timestampLen = 13;
    private static final int datetimeLen = 17;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private static final AtomicInteger timestampSeq = new AtomicInteger(0);
    private static final AtomicInteger datetimeSeq = new AtomicInteger(0);
    private static final char PAD_ZERO = '0';

    /**
     * 生成一个UUID
     */
    public static String uuid() {

        return UUID.randomUUID().toString().replace(StringPool.DASH, StringPool.EMPTY);
    }

    public static String timestampId(int len) {

        String suffix = StringPool.EMPTY;
        if (len > timestampLen) {
            int suffixLen = len - timestampLen;
            int suffixSeq = Double.valueOf(timestampSeq.incrementAndGet() % Math.pow(10, suffixLen)).intValue();
            suffix = StringUtils.leftPad(String.valueOf(suffixSeq), len - timestampLen, PAD_ZERO);

        }
        return System.currentTimeMillis() + suffix;
    }

    public static String datetimeId(int len) {

        String datetime = LocalDateTime.now().format(formatter);
        String suffix = StringPool.EMPTY;
        if (len > datetimeLen) {
            int suffixLen = len - datetimeLen;
            int suffixSeq = Double.valueOf(datetimeSeq.incrementAndGet() % Math.pow(10, suffixLen)).intValue();
            suffix = StringUtils.leftPad(String.valueOf(suffixSeq), len - datetimeLen, PAD_ZERO);
        }
        return datetime + suffix;
    }
}
