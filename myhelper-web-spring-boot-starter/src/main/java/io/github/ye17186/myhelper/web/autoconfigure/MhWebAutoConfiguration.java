package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.event.DelayedEventQueueHolder;
import io.github.ye17186.myhelper.core.event.EventPublisher;
import io.github.ye17186.myhelper.web.advice.MhWebExceptionHandler;
import io.github.ye17186.myhelper.web.aspect.log.SysLogAspect;
import io.github.ye17186.myhelper.web.filter.MhRequestContextFilter;
import io.github.ye17186.myhelper.web.filter.RequestLogService;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
@AutoConfiguration
@Import({
        MhWebCorsAutoConfiguration.class,
        MhWebExceptionHandler.class,
        MhWebLoginInterceptorAutoConfiguration.class,
        MhWebAsyncAutoConfiguration.class})
@EnableConfigurationProperties(MhWebProperties.class)
public class MhWebAutoConfiguration {

    @Autowired
    MhWebProperties webProperties;

    @Bean
    public MhRequestContextFilter mhRequestContextFilter(@Autowired(required = false) RequestLogService service) {

        return new MhRequestContextFilter(service);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.enable-log-aspect", havingValue = "true",
            matchIfMissing = true)
    public SysLogAspect sysLogAspect() {

        return new SysLogAspect();
    }

    @Bean
    public EventPublisher eventPublisher(ApplicationContext context) {

        return new EventPublisher(context, webProperties.getDelayEvent().isEnabled());
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.delay-event.enabled", havingValue = "true",
            matchIfMissing = true)
    public DelayedEventQueueHolder delayedEventQueueHolder(EventPublisher publisher, AsyncConfigurer configure) {

        return new DelayedEventQueueHolder(publisher, (ThreadPoolTaskExecutor) configure.getAsyncExecutor());
    }
}
