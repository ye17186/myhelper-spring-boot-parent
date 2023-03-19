package io.github.ye17186.myhelper.web.filter;

import io.github.ye17186.myhelper.core.web.context.RequestInfo;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
public interface RequestLogService {

    /**
     * Http请求日志处理，可用于落库
     *
     * @param log 请求信息
     */
    void handle(RequestInfo log);
}
