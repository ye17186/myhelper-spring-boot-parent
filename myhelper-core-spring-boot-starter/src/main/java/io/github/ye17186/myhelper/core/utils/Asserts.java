package io.github.ye17186.myhelper.core.utils;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * @author ye17186
 * @since 2022-10-15
 */
@Slf4j
public class Asserts {

    /**
     * 不为null
     *
     * @param obj    断言对象
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notNull(@Nullable Object obj, String msg, Object... params) {

        if (obj == null) {
            throw new BizException(ErrorCode.BIZ_EX.getCode(), formatMsg(msg, params));
        }
    }

    /**
     * 不为null
     *
     * @param obj    断言对象
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notNull(@Nullable Object obj, int code, String msg, Object... params) {

        if (obj == null) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 为null
     *
     * @param obj    断言对象
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isNull(@Nullable Object obj, String msg, Object... params) {

        if (obj != null) {
            throw new BizException(ErrorCode.BIZ_EX.getCode(), formatMsg(msg, params));
        }
    }

    /**
     * 为null
     *
     * @param obj    断言对象
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isNull(@Nullable Object obj, int code, String msg, Object... params) {

        if (obj != null) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 为空
     *
     * @param str    断言字符串
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notEmpty(@Nullable String str, String msg, Object... params) {

        notEmpty(str, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 为空
     *
     * @param str    断言字符串
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notEmpty(@Nullable String str, int code, String msg, Object... params) {

        if (StringUtils.isEmpty(str)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言集合不为空
     *
     * @param collection 断言集合
     * @param msg        错误消息
     * @param params     参数
     */
    public static void notEmpty(@Nullable Collection<?> collection, String msg, Object... params) {

        notEmpty(collection, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言集合不为空
     *
     * @param collection 断言集合
     * @param code       错误码
     * @param msg        错误消息
     * @param params     参数
     */
    public static void notEmpty(@Nullable Collection<?> collection, int code, String msg, Object... params) {

        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言Map不为空
     *
     * @param map    断言Map
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notEmpty(@Nullable Map<?, ?> map, String msg, Object... params) {

        notEmpty(map, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言Map不为空
     *
     * @param map    断言Map
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void notEmpty(@Nullable Map<?, ?> map, int code, String msg, Object... params) {

        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言字符串为空
     *
     * @param str    断言字符串
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isEmpty(@Nullable String str, String msg, Object... params) {

        isEmpty(str, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言字符串为空
     *
     * @param str    断言字符串
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isEmpty(@Nullable String str, int code, String msg, Object... params) {

        if (StringUtils.isNotEmpty(str)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言集合为空
     *
     * @param collection 断言集合
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isEmpty(@Nullable Collection<?> collection, String msg, Object... params) {

        isEmpty(collection, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言集合为空
     *
     * @param collection 断言集合
     * @param code       错误码
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isEmpty(@Nullable Collection<?> collection, int code, String msg, Object... params) {

        if (CollectionUtils.isNotEmpty(collection)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言Map为空
     *
     * @param map    断言Map
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isEmpty(@Nullable Map<?, ?> map, String msg, Object... params) {

        isEmpty(map, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言Map为空
     *
     * @param map    断言Map
     * @param code   错误码
     * @param msg    错误消息
     * @param params 参数
     */
    public static void isEmpty(@Nullable Map<?, ?> map, int code, String msg, Object... params) {

        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言为true
     *
     * @param expression 断言表达式
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isTrue(boolean expression, String msg, Object... params) {

        isTrue(expression, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言为true
     *
     * @param expression 断言表达式
     * @param code       错误码
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isTrue(boolean expression, int code, String msg, Object... params) {

        if (!expression) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 断言为false
     *
     * @param expression 断言表达式
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isFalse(boolean expression, String msg, Object... params) {

        isFalse(expression, ErrorCode.BIZ_EX.getCode(), msg, params);
    }

    /**
     * 断言为false
     *
     * @param expression 断言表达式
     * @param code       错误码
     * @param msg        错误消息
     * @param params     参数
     */
    public static void isFalse(boolean expression, int code, String msg, Object... params) {

        if (expression) {
            throw new BizException(code, formatMsg(msg, params));
        }
    }

    /**
     * 格式化错误消息
     *
     * @param format 消息模板
     * @param params 消息参数
     */
    private static String formatMsg(String format, Object[] params) {

        return MessageFormatter.arrayFormat(format, params).getMessage();
    }
}
