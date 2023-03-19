package com.ye186.thirdparty.myhelper.minio.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye1718620
 * @date 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.minio")
public class MhMinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
}
