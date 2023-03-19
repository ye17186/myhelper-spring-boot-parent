package com.ye186.thirdparty.myhelper.web.filter;

import com.ye186.thirdparty.myhelper.core.web.context.RequestInfo;

/**
 * @author ye1718620
 * @date 2023-02-09
 */
public interface RequestLogService {

    /**
     * Http请求日志处理，可用于落库
     *
     * @param log 请求信息
     */
    void handle(RequestInfo log);
}
