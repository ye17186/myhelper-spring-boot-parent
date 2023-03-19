package io.github.ye17186.myhelper.core.autoconfigure;

import io.github.ye17186.myhelper.core.jackson.ObjectMappers;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson配置
 *
 * @author ye17186
 * @since 2022-10-13
 */
@AutoConfiguration(before = JacksonAutoConfiguration.class)
public class MhJacksonAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {

        return ObjectMappers.builder();
    }
}
