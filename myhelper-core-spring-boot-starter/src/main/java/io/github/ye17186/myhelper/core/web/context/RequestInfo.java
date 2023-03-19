package io.github.ye17186.myhelper.core.web.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ye17186
 * @since 2022-10-12
 */
@Setter
@Getter
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 6618139012795180929L;

    /**
     * 请求唯一标识ID
     */
    private String requestId;

    /**
     * 请求发起时间
     */
    private LocalDateTime requestTime;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 请求http地址
     */
    private String httpUri;

    /**
     * 请求http方法
     */
    private String httpMethod;

    /**
     * 请求响应时间
     */
    private LocalDateTime responseTime;

    /**
     * 请求总耗时，单位ms
     */
    private long duration;
}
