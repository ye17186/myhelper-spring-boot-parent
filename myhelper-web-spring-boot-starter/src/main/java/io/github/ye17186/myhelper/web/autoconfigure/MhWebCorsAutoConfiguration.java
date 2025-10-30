package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebCorsProperties;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ye17186
 * @since 2023-02-09
 */
@Slf4j
@ConditionalOnProperty(name = "spring.my-helper.web.cors.enabled", havingValue = "true", matchIfMissing = true)
@AutoConfiguration
public class MhWebCorsAutoConfiguration {

    @Autowired
    MhWebProperties properties;

    @Bean
    public CorsFilter corsFilter() {

        MhWebCorsProperties corsProperties = properties.getCors();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(corsProperties.getOrigins());
        config.setAllowedHeaders(corsProperties.getHeaders());
        config.setAllowedMethods(corsProperties.getMethods());
        config.setAllowCredentials(corsProperties.getAllowCredentials());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsProperties.getMapping(), config);

        log.info("【MyHelper】【Web】 允许跨域配置成功. mapping: {}， origins: {}, headers: {}, methods: {}, credentials: {}",
                corsProperties.getMapping(),
                config.getAllowedOrigins(),
                config.getAllowedHeaders(),
                config.getAllowedMethods(),
                config.getAllowCredentials());
        return new CorsFilter(source);
    }
}
