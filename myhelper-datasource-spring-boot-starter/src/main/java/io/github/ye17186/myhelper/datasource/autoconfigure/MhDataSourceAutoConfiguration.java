package io.github.ye17186.myhelper.datasource.autoconfigure;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import io.github.ye17186.myhelper.datasource.autoconfigure.properties.MhDataSourceProperties;
import io.github.ye17186.myhelper.datasource.event.MhEncDataSourceInitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye17186
 * @date 2023/9/18
 */
@Slf4j
@AutoConfiguration(before = DynamicDataSourceAutoConfiguration.class)
@EnableConfigurationProperties(MhDataSourceProperties.class)
public class MhDataSourceAutoConfiguration {

    @Autowired
    MhDataSourceProperties properties;

    @Bean
    public MhEncDataSourceInitEvent mhEncDataSourceInitEvent() {

        return new MhEncDataSourceInitEvent(properties);
    }
}
