package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.token.MhTokenService;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAuthInterceptorProperties;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import io.github.ye17186.myhelper.web.filter.RequestLogService;
import io.github.ye17186.myhelper.web.interceptor.api.MhApiSignInterceptor;
import io.github.ye17186.myhelper.web.interceptor.trace.MhRequestTraceInterceptor;
import io.github.ye17186.myhelper.web.interceptor.login.MhLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye17186
 */
@Slf4j
@AutoConfiguration
public class MhWebInterceptorAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    MhWebProperties properties;

    @Autowired(required = false)
    MhTokenService tokenService;

    @Autowired(required = false)
    RequestLogService requestLogService;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {

        // 拦截器注册，必须保证顺序
        addRequestTraceInterceptor(registry);
        addApiAuthInterceptor(registry);
        addLoginInterceptor(registry);
    }

    private void addRequestTraceInterceptor(InterceptorRegistry registry) {

        if (properties.getRequestTraceInterceptor().isEnabled()) {
            registry.addInterceptor(new MhRequestTraceInterceptor(properties.getRequestTraceInterceptor(), requestLogService));
            log.info("【MyHelper】【Web】注册RequestTrace拦截器完成.");
        }
    }

    private void addApiAuthInterceptor(InterceptorRegistry registry) {

        MhWebApiAuthInterceptorProperties apiAuthProperties = properties.getApiSignInterceptor();
        if (apiAuthProperties.isEnabled()) {
            MhApiSignInterceptor interceptor = new MhApiSignInterceptor(apiAuthProperties);

            registry.addInterceptor(interceptor)
                    .addPathPatterns(apiAuthProperties.getIncludes())
                    .excludePathPatterns(apiAuthProperties.getExcludes());
            log.info("【MyHelper】【Web】注册API接口鉴权拦截器完成.");
        }
    }

    private void addLoginInterceptor(InterceptorRegistry registry) {

        if (CollectionUtils.isNotEmpty(properties.getLoginInterceptors())) {
            properties.getLoginInterceptors().forEach(config -> {
                MhLoginInterceptor interceptor = new MhLoginInterceptor(tokenService, config.getUserType());

                registry.addInterceptor(interceptor)
                        .addPathPatterns(config.getIncludes())
                        .excludePathPatterns(config.getExcludes());
            });
            log.info("【MyHelper】【Web】注册登录拦截器完成.");
        }
    }
}
