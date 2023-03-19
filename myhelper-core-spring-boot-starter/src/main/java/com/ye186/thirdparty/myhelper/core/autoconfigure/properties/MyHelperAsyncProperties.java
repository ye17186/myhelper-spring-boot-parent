package com.ye186.thirdparty.myhelper.core.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye1718620
 * @date 2023-02-09
 */
@Setter
@Getter
@ConfigurationProperties(prefix = MyHelperAsyncProperties.PREFIX)
public class MyHelperAsyncProperties {

    public static final String PREFIX = "spring.my-helper.async";

    private String threadNamePrefix = "mh-task-pool-";
    private int corePoolSize = 10;
    private int maxPoolSize = 50;
}
