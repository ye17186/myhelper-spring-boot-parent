package io.github.ye17186.myhelper.core.utils;


import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间日期工具类
 *
 * @author ye17186
 * @since 2020/2/18 11:48
 */
public class DateUtils {

    private DateUtils() {
    }

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * LocalDate转Date
     *
     * @param localDate 当前日期
     */
    public static Date toDate(LocalDate localDate) {
        return localDate == null ? null
                : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转Date
     *
     * @param dateTime 日期时间
     */
    public static Date toDate(LocalDateTime dateTime) {
        return dateTime == null ? null
                : Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串解析成LocalDateTime
     *
     * @param str 时间字符串，默认格式为yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime parse(CharSequence str) {
        return parse(str, DEFAULT_FORMAT);
    }

    /**
     * 指定格式字符串解析成LocalDateTime
     *
     * @param str     时间字符串
     * @param pattern 指定格式
     */
    public static LocalDateTime parse(CharSequence str, String pattern) {
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串解析成Date
     *
     * @param str 时间字符串，默认格式为yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String str) {
        return parse(str, DEFAULT_FORMAT);
    }

    /**
     * 指定格式字符串解析成Date
     *
     * @param str     时间字符串
     * @param pattern 指定格式
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR.getCode(), "时间字符串解析错误", e);
        }
    }

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date == null ? null
                : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date转LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String format(LocalDateTime dateTime) {

        return format(dateTime, DEFAULT_FORMAT);
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     */
    public static String format(Date date) {
        return format(toLocalDateTime(date));
    }

    /**
     * 格式化时间
     *
     * @param dataTime LocalDateTime时间
     * @param pattern  格式
     */
    public static String format(LocalDateTime dataTime, String pattern) {
        return dataTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化时间（yyyy-MM-dd HH:mm:ss）
     *
     * @param date    日期
     * @param pattern 格式
     */
    public static String format(Date date, String pattern) {
        return format(toLocalDateTime(date), pattern);
    }

    /**
     * 格式化日期（yyyy-MM-dd）
     *
     * @param localDate LocalDate日期
     */
    public static String format(LocalDate localDate) {
        return format(localDate, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化日期
     *
     * @param localDate LocalDate日期
     * @param pattern   格式
     */
    public static String format(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化时间（HH:mm:ss）
     *
     * @param localTime LocalTime时间
     */
    public static String format(LocalTime localTime) {
        return format(localTime, DEFAULT_TIME_FORMAT);
    }

    /**
     * 格式化时间
     *
     * @param localTime LocalTime时间
     * @param pattern   格式
     */
    public static String format(LocalTime localTime, String pattern) {
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * LocalDateTime转毫秒
     *
     * @param dateTime LocalDateTime时间
     */
    public static long toMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDateTime转秒
     *
     * @param dateTime LocalDateTime时间
     */
    public static long toSecond(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }
}
