package com.ye186.thirdparty.myhelper.caffeine.autoconfigure;

import com.ye186.thirdparty.myhelper.caffeine.cache.CaffeineCacheManager;
import com.ye186.thirdparty.myhelper.caffeine.autoconfigure.properties.CaffeineCacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ye17186
 * @date 2022-11-07
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CaffeineCacheProperties.class)
public class MhCaffeineAutoConfiguration {

    @Autowired
    CaffeineCacheProperties properties;

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {

        return new CaffeineCacheManager(properties);
    }
}
