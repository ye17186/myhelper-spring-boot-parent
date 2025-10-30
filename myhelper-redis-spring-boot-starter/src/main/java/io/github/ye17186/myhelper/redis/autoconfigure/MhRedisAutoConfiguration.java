package io.github.ye17186.myhelper.redis.autoconfigure;

import io.github.ye17186.myhelper.redis.autoconfigure.facotry.RedisCacheManagerFactory;
import io.github.ye17186.myhelper.redis.autoconfigure.properties.RedisCacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author ye17186
 * @since 2022-11-07
 */
@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisCacheProperties.class)
public class MhRedisAutoConfiguration {

    @Autowired
    RedisCacheProperties properties;

    @Autowired
    RedisConnectionFactory factory;

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager redisCacheManager() {

        RedisCacheManager manager = RedisCacheManagerFactory.create(properties, factory);
        log.info("【MyHelper】【Cache】Redis缓存管理器注册完成.");
        return manager;
    }
}
