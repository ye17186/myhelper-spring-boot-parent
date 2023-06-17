package io.github.ye17186.myhelper.imail.aotuconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye17186
 * @since 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.imail")
public class MhImailProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = Boolean.TRUE;
}
