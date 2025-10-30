package io.github.ye17186.myhelper.caffeine.factory;

import io.github.ye17186.myhelper.caffeine.autoconfigure.properties.CaffeineCacheProperties;
import io.github.ye17186.myhelper.caffeine.cache.CaffeineCacheManager;

/**
 * 自定义Caffeine缓存管理器
 *
 * @author ye17186
 * @since 2022-11-07
 */
public class CaffeineCacheManagerFactory  {

    public static CaffeineCacheManager create(CaffeineCacheProperties properties) {

        return new CaffeineCacheManager(properties);
    }
}
