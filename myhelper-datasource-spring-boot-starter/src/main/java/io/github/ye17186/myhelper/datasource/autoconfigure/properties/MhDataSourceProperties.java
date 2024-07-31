package io.github.ye17186.myhelper.datasource.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye17186
 * @date 2024/5/30
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.datasource")
public class MhDataSourceProperties {
}
