package io.github.ye17186.myhelper.redis.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ye17186
 * @since 2022-11-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.cache")
public class RedisCacheProperties {

    /**
     * 缓存前缀
     */
    private String prefix = "myhelper::";

    /**
     * 默认存活时间，单位：秒
     */
    private Long ttl = 3600L;

    /**
     * 缓存配置。key：缓存名，value：过期时间
     */
    private Map<String, Long> cacheSpecs = new HashMap<>();
}
