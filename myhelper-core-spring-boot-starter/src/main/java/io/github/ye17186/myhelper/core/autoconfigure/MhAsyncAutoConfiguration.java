package io.github.ye17186.myhelper.core.autoconfigure;

import io.github.ye17186.myhelper.core.async.ThreadPoolTaskExecutorWrapper;
import io.github.ye17186.myhelper.core.autoconfigure.properties.MyHelperAsyncProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
@AutoConfiguration
@EnableConfigurationProperties(MyHelperAsyncProperties.class)
public class MhAsyncAutoConfiguration implements AsyncConfigurer {

    private final MyHelperAsyncProperties properties;

    public MhAsyncAutoConfiguration(MyHelperAsyncProperties properties) {
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
