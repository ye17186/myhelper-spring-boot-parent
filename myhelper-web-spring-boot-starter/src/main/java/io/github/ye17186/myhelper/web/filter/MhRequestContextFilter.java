package io.github.ye17186.myhelper.web.filter;

import io.github.ye17186.myhelper.core.utils.IdUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import io.github.ye17186.myhelper.core.web.context.RequestInfo;
import io.github.ye17186.myhelper.web.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 请求上下文过滤器
 *
 * @author ye17186
 * @since 2022-10-12
 */
@Slf4j
public class MhRequestContextFilter extends OncePerRequestFilter {

    private final RequestLogService logService;

    public MhRequestContextFilter(RequestLogService service) {
        this.logService = service;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        RequestInfo info = initRequest(request);
        RequestContext.set(info);
        try {
            filterChain.doFilter(request, response);
        } finally {
            info.setResponseTime(LocalDateTime.now());
            info.setDuration(Duration.between(info.getRequestTime(), info.getResponseTime()).toMillis());
            if (logService != null) {
                logService.handle(info);
            }
            RequestContext.remove();
        }
    }

    /**
     * 初始化请求
     *
     * @param servletRequest http请求
     */
    public RequestInfo initRequest(ServletRequest servletRequest) {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        RequestInfo request = new RequestInfo();
        request.setRequestId(IdUtils.uuid());
        request.setRequestTime(LocalDateTime.now());
        request.setClientIp(RequestUtils.getClientIp(httpRequest));
        request.setHttpMethod(httpRequest.getMethod());
        request.setHttpUri(httpRequest.getRequestURI());
        return request;
    }
}
