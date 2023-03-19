package com.ye186.thirdparty.myhelper.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务工具类
 *
 * @author ye17186
 * @date 2021-06-17
 */
@Slf4j
public class FutureUtils {

    private FutureUtils() {
    }

    /**
     * 获取异步执行结果，超时返回null
     *
     * @param future  异步任务
     * @param timeout 超时时间
     * @param <T>     结果泛型
     */
    public static <T> T get(Future<T> future, int timeout) {

        return get(future, timeout, null);
    }

    /**
     * 获取异步执行结果，任务超时返回指定默认结果{@code defaultResult}
     *
     * @param future        异步任务
     * @param timeout       超时时间
     * @param defaultResult 超时默认结果
     * @param <T>           结果泛型
     */
    public static <T> T get(Future<T> future, int timeout, T defaultResult) {

        T result = defaultResult;
        try {
            result = future.get(timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("[ET] 获取异步结果异常.", e);
        }
        return result;
    }
}
