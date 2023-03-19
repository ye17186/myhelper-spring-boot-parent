package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.async.ThreadPoolTaskExecutorWrapper;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebAsyncProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
@AutoConfiguration
@EnableConfigurationProperties(MhWebAsyncProperties.class)
public class MhWebAsyncAutoConfiguration implements AsyncConfigurer {

    private final MhWebAsyncProperties properties;

    public MhWebAsyncAutoConfiguration(MhWebAsyncProperties properties) {
        this.properties = properties;
    }

    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.initialize();

        return ThreadPoolTaskExecutorWrapper.wrap(executor);
    }
}
