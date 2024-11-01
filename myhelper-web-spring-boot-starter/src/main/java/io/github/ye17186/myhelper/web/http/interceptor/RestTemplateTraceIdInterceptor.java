package io.github.ye17186.myhelper.web.http.interceptor;

import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.web.context.RequestContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * RestTemplate TraceId请求头拦截器
 *
 * @author yemeng20
 */
public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {

    private final String header;

    public RestTemplateTraceIdInterceptor(String header) {
        this.header = header;
    }

    @Override
    public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body, @NonNull ClientHttpRequestExecution execution) throws IOException {

        String traceId = RequestContext.requestId();
        if (StringUtils.isNotEmpty(traceId)) {
            request.getHeaders().add(header, traceId);
        }
        return execution.execute(request, body);
    }
}
