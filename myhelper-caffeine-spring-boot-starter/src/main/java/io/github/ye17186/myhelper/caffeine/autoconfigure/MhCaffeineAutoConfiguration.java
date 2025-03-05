package io.github.ye17186.myhelper.caffeine.autoconfigure;

import io.github.ye17186.myhelper.caffeine.cache.CaffeineCacheManager;
import io.github.ye17186.myhelper.caffeine.autoconfigure.properties.CaffeineCacheProperties;
import io.github.ye17186.myhelper.caffeine.factory.CaffeineCacheManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ye17186
 * @since 2022-11-07
 */
@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties(CaffeineCacheProperties.class)
public class MhCaffeineAutoConfiguration {

    @Autowired
    CaffeineCacheProperties properties;

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager caffeineCacheManager() {

        CaffeineCacheManager manager = CaffeineCacheManagerFactory.create(properties);
        log.info("【MyHelper】【Cache】Caffeine缓存管理器注册完成.");
        return manager;
    }
}
