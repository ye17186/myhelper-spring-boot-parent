package io.github.ye17186.myhelper.job.aotuconfigure;

import io.github.ye17186.myhelper.job.aotuconfigure.properties.MhJobProperties;
import io.github.ye17186.myhelper.job.executor.MhJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 基于XXL-JOB的分布式定时任务
 *
 * @author ye17186
 * @date 2023-04-17
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MhJobProperties.class)
public class MhJobAutoConfiguration {

    @Autowired
    MhJobProperties properties;

    @Bean
    public MhJobSpringExecutor mhJobSpringExecutor() {

        MhJobSpringExecutor executor = new MhJobSpringExecutor();
        executor.setAdminAddresses(properties.getServerUrl());
        executor.setAccessToken(properties.getAccessToken());
        executor.setAppname(properties.getAppName());
        executor.setLogPath(properties.getLogPath());
        executor.setLogRetentionDays(properties.getLogRetentionDays());
        log.info("【MyHelper】【JOB】客户端初始化成功.");
        return executor;
    }
}
