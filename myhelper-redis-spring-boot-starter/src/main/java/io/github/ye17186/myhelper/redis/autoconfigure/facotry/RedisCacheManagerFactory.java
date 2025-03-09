package io.github.ye17186.myhelper.redis.autoconfigure.facotry;

import io.github.ye17186.myhelper.redis.autoconfigure.properties.RedisCacheProperties;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ye17186
 */
public class RedisCacheManagerFactory {

    private static final RedisSerializer<String> KEY_SERIALIZER = new StringRedisSerializer();
    private static final GenericJackson2JsonRedisSerializer VALUE_SERIALIZER = new GenericJackson2JsonRedisSerializer();

    public static RedisCacheManager create(RedisCacheProperties properties, RedisConnectionFactory factory) {

        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        properties.getCacheSpecs().forEach((k, v) -> configMap.put(k, buildConfig(properties.getPrefix(), v)));
        return RedisCacheManager.builder(factory)
                .cacheDefaults(buildConfig(properties.getPrefix(), properties.getTtl()))
                .withInitialCacheConfigurations(configMap)
                .build();
    }

    private static RedisCacheConfiguration buildConfig(String prefix, long ttl) {

        return RedisCacheConfiguration
                .defaultCacheConfig()
                .prefixCacheNameWith(prefix)
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(KEY_SERIALIZER))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(VALUE_SERIALIZER))
                .entryTtl(Duration.ofSeconds(ttl));
    }
}
