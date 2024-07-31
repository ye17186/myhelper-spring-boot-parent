package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.async.ThreadPoolTaskExecutorWrapper;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ye17186
 * @since 2023-02-09
 */
@Slf4j
@AutoConfiguration
public class MhWebAsyncAutoConfiguration implements AsyncConfigurer {

    @Autowired
    private MhWebProperties properties;

    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getThreadPool().getCoreSize());
        executor.setMaxPoolSize(properties.getThreadPool().getMaxSize());
        executor.setThreadNamePrefix(properties.getThreadPool().getNamePrefix());
        executor.setKeepAliveSeconds(properties.getThreadPool().getKeepAliveSeconds());
        executor.initialize();

        log.info("【MyHelper】【Web】 异步线程池增强执行器注册完成.");
        return ThreadPoolTaskExecutorWrapper.wrap(executor);
    }
}
