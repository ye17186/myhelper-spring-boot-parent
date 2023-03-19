package com.ye186.thirdparty.myhelper.core.autoconfigure;

import com.ye186.thirdparty.myhelper.core.autoconfigure.properties.MyHelperCoreProperties;
import com.ye186.thirdparty.myhelper.core.event.DelayedEventQueueHolder;
import com.ye186.thirdparty.myhelper.core.event.EventPublisher;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye1718620
 * @date 2023-02-09
 */
@AutoConfiguration
@EnableConfigurationProperties(MyHelperCoreProperties.class)
public class MhCoreAutoConfiguration {

    @Bean
    public EventPublisher eventPublisher(ApplicationContext context, MyHelperCoreProperties properties) {

        return new EventPublisher(context, properties);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.delay-event.enabled", havingValue = "true",
            matchIfMissing = true)
    public DelayedEventQueueHolder delayedEventQueueHolder(EventPublisher publisher, AsyncConfigurer configure) {

        return new DelayedEventQueueHolder(publisher, (ThreadPoolTaskExecutor) configure.getAsyncExecutor());
    }
}
