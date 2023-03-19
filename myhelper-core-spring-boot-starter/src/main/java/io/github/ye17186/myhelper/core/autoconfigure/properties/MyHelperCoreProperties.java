package io.github.ye17186.myhelper.core.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
@Setter
@Getter
@ConfigurationProperties(prefix = MyHelperCoreProperties.PREFIX)
public class MyHelperCoreProperties {

    public static final String PREFIX = "spring.my-helper.core";

    private DelayEventConfig delayEvent = new DelayEventConfig();

    @Setter
    @Getter
    public static class DelayEventConfig {

        private Boolean enabled = true;
    }
}
