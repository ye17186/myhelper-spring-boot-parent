package io.github.ye17186.myhelper.web.annotation;

import io.github.ye17186.myhelper.web.aspect.log.ActionType;
import io.github.ye17186.myhelper.web.aspect.log.LogTarget;

import java.lang.annotation.*;

/**
 * @author ye17186
 * @since 2022-09-30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLogPoint {

    /**
     * 操作名
     */
    String action() default "unknown";

    ActionType type() default ActionType.QUERY;

    /**
     * 是否忽略输入
     */
    boolean ignoreInput() default false;

    /**
     * 是否忽略输出
     */
    boolean ignoreOutput() default false;

    /**
     * 敏感参数
     */
    String[] sensitiveParams() default {};

    /**
     * 目标类型：CONTROLLER：controller日志, SERVICE：service日志, Mapper：dao日志, METHOD：普通方法日志
     */
    LogTarget target() default LogTarget.CONTROLLER;
}
