package io.github.ye17186.myhelper.minio.properties;

import io.github.ye17186.myhelper.core.oss.type.OssType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye1718620
 * @since 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.oss")
public class MhMinioProperties {

    private OssType type = OssType.MINIO;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * key
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 是否启用pathStyle
     */
    private boolean enabledPathStyle = true;
}
