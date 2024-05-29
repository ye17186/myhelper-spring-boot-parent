package io.github.ye17186.myhelper.datasource.autoconfigure;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAssistConfiguration;
import io.github.ye17186.myhelper.datasource.autoconfigure.properties.MhDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ye17186
 * @date 2023/9/18
 */
@Slf4j
@Configuration
@AutoConfigureBefore({DynamicDataSourceAssistConfiguration.class})
@EnableConfigurationProperties(MhDataSourceProperties.class)
public class MhDataSourceAutoConfiguration {
}
