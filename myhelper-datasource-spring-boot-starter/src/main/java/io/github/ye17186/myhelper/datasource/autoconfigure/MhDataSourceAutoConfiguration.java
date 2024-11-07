package io.github.ye17186.myhelper.datasource.autoconfigure;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import io.github.ye17186.myhelper.datasource.autoconfigure.properties.MhDataSourceProperties;
import io.github.ye17186.myhelper.datasource.event.MhEncDataSourceInitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ye17186
 * @date 2023/9/18
 */
@Slf4j
@Configuration
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class})
@EnableConfigurationProperties(MhDataSourceProperties.class)
public class MhDataSourceAutoConfiguration {

    @Autowired
    MhDataSourceProperties properties;

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.datasource.enable-enc", havingValue = "true",
            matchIfMissing = true)
    public MhEncDataSourceInitEvent mhEncDataSourceInitEvent() {

        return new MhEncDataSourceInitEvent(properties);
    }
}
