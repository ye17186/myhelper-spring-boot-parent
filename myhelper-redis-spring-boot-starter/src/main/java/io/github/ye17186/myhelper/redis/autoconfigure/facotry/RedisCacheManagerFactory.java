package io.github.ye17186.myhelper.redis.autoconfigure.facotry;

import io.github.ye17186.myhelper.redis.autoconfigure.properties.RedisCacheProperties;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yemeng20
 */
public class RedisCacheManagerFactory {

    public static RedisCacheManager create(RedisCacheProperties properties, RedisConnectionFactory factory) {

        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        properties.getCacheSpecs().forEach((k, v) -> configMap.put(k, buildConfig(v)));
        return RedisCacheManager.builder(factory)
                .cacheDefaults(buildConfig(properties.getTtl()))
                .withInitialCacheConfigurations(configMap)
                .build();
    }

    private static RedisCacheConfiguration buildConfig(long ttl) {

        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(ttl));
    }
}
