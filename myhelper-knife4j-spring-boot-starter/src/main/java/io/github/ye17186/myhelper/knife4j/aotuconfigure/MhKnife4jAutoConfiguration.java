package io.github.ye17186.myhelper.knife4j.aotuconfigure;

import io.github.ye17186.myhelper.knife4j.converter.EnumSchemaConverter;
import io.github.ye17186.myhelper.knife4j.customizer.KnifeExtOpenApiCustomizer;
import io.github.ye17186.myhelper.knife4j.aotuconfigure.properties.MhKnife4jProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye1718620
 * @since 2023-02-08
 */
@AutoConfiguration
@EnableConfigurationProperties(MhKnife4jProperties.class)
public class MhKnife4jAutoConfiguration {

    @Bean
    public KnifeExtOpenApiCustomizer knifeExtOpenApiCustomizer(MhKnife4jProperties properties) {

        return new KnifeExtOpenApiCustomizer(properties);
    }

    @Bean
    public EnumSchemaConverter enumModelConverter() {

        return new EnumSchemaConverter();
    }
}
