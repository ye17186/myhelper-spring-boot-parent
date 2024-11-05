package io.github.ye17186.myhelper.web.interceptor.trace;

import io.github.ye17186.myhelper.core.utils.IdUtils;
import io.github.ye17186.myhelper.core.utils.StringPool;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import io.github.ye17186.myhelper.core.web.context.RequestInfo;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebRequestTraceInterceptorProperties;
import io.github.ye17186.myhelper.web.consts.MhWebConstants;
import io.github.ye17186.myhelper.web.filter.RequestLogService;
import io.github.ye17186.myhelper.web.interceptor.MhInterceptor;
import io.github.ye17186.myhelper.web.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author ye17186
 */
@Slf4j
public class MhRequestTraceInterceptor extends MhInterceptor {

    private final MhWebRequestTraceInterceptorProperties properties;
    private final RequestLogService logService;


    public MhRequestTraceInterceptor(MhWebRequestTraceInterceptorProperties properties, RequestLogService service) {
        this.properties = properties;
        this.logService = service;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        String traceId = request.getHeader(properties.getHeaderPrefix() + StringPool.DASH + MhWebConstants.HEADER_TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = IdUtils.uuid();
        }

        RequestInfo info = initRequest(request, traceId);
        MDC.put(MhWebConstants.MDC_TRACE_ID, info.getRequestId());
        RequestContext.set(info);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {

        RequestInfo info = RequestContext.get();
        info.setResponseTime(LocalDateTime.now());
        info.setDuration(Duration.between(info.getRequestTime(), info.getResponseTime()).toMillis());
        if (properties.isRequestLogEnabled() && logService != null) {
            logService.handle(info);
        }
        RequestContext.remove();
        MDC.remove(MhWebConstants.MDC_TRACE_ID);
    }

    /**
     * 初始化请求
     *
     * @param servletRequest http请求
     */
    public RequestInfo initRequest(ServletRequest servletRequest, String traceId) {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        RequestInfo request = new RequestInfo();
        request.setRequestId(traceId);
        request.setRequestTime(LocalDateTime.now());
        request.setClientIp(RequestUtils.getClientIp(httpRequest));
        request.setHttpMethod(httpRequest.getMethod());
        request.setHttpUri(httpRequest.getRequestURI());
        return request;
    }
}
