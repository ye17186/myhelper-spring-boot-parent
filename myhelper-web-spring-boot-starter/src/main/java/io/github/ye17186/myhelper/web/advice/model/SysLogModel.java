package io.github.ye17186.myhelper.web.advice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ye17186
 * @date 2024/5/29
 */
@Setter
@Getter
public class SysLogModel implements Serializable {

    /**
     * 请求唯一标识
     */
    private String requestId;

    /**
     * 操作名
     */
    private String action;

    /**
     * 目标
     */
    private String target;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String method;

    /**
     * 入参
     */
    private Object input;

    /**
     * 返回结果
     */
    private Object output;

    /**
     * 返回类型（SUCCESS=成功；FAIL=失败）
     */
    private String status;

    /**
     * 异常信息
     */
    private String exMsg;

    /**
     * 进入时间
     */
    private LocalDateTime startTime;

    /**
     * 耗时，单位毫秒
     */
    private Long duration;
}
