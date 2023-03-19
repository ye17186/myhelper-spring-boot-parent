package io.github.ye17186.myhelper.caffeine.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.ye17186.myhelper.caffeine.autoconfigure.properties.CaffeineCacheProperties;
import org.springframework.lang.NonNull;

import java.util.concurrent.TimeUnit;

/**
 * 自定义Caffeine缓存管理器
 *
 * @author ye17186
 * @since 2022-11-07
 */
public class CaffeineCacheManager extends org.springframework.cache.caffeine.CaffeineCacheManager {

    private final CaffeineCacheProperties properties;

    public CaffeineCacheManager(CaffeineCacheProperties properties) {

        this.properties = properties;
    }

    @Override
    @NonNull
    protected Cache<Object, Object> createNativeCaffeineCache(@NonNull String name) {

        Long timeout = properties.getCacheSpecs().get(name);
        if (timeout == null) {
            timeout = properties.getTimeout();
        }

        final Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .expireAfterWrite(timeout, TimeUnit.SECONDS);
        return caffeine.build();
    }
}
