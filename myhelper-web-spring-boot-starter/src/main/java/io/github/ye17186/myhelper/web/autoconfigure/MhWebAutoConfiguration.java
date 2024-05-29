package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.event.DelayedEventQueueHolder;
import io.github.ye17186.myhelper.core.event.EventPublisher;
import io.github.ye17186.myhelper.web.advice.handler.MhWebExceptionHandler;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import io.github.ye17186.myhelper.web.filter.DefaultRequestLogService;
import io.github.ye17186.myhelper.web.filter.MhRequestContextFilter;
import io.github.ye17186.myhelper.web.filter.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye17186
 * @since 2023-02-09
 */
@AutoConfiguration
@Import({
        MhWebCorsAutoConfiguration.class,
        MhWebExceptionHandler.class,
        MhWebLoginInterceptorAutoConfiguration.class,
        MhWebAsyncAutoConfiguration.class,
        MhWebApiAdviceAutoConfiguration.class})
@EnableConfigurationProperties(MhWebProperties.class)
public class MhWebAutoConfiguration {

    @Autowired
    MhWebProperties webProperties;

    @Bean
    @ConditionalOnMissingBean(RequestLogService.class)
    public RequestLogService mhRequestLogService() {

        return new DefaultRequestLogService();
    }

    @Bean
    public MhRequestContextFilter mhRequestContextFilter(@Autowired(required = false) RequestLogService service) {

        return new MhRequestContextFilter(service);
    }

    @Bean
    public EventPublisher eventPublisher(ApplicationContext context) {

        return new EventPublisher(context, webProperties.getDelayEvent().isEnabled());
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.delay-event.enabled", havingValue = "true",
            matchIfMissing = false)
    public DelayedEventQueueHolder delayedEventQueueHolder(EventPublisher publisher, AsyncConfigurer configure) {

        return new DelayedEventQueueHolder(publisher, (ThreadPoolTaskExecutor) configure.getAsyncExecutor());
    }
}
