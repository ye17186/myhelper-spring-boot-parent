package io.github.ye17186.myhelper.caffeine.autoconfigure.properties;

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
public class CaffeineCacheProperties {

    /**
     * 缓存前置
     */
    private String prefix = "myhelper::";

    /**
     * 默认超时时间，单位：秒
     */
    private Long timeout = 3600L;

    /**
     * 默认最大大小
     */
    private Integer maxsize = 100;

    /**
     * 缓存配置。key：缓存名，value：过期时间
     */
    private Map<String, Long> cacheSpecs = new HashMap<>();
}
