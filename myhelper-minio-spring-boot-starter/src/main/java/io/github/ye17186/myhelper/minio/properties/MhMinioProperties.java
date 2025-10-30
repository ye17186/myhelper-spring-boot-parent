package io.github.ye17186.myhelper.minio.properties;

import io.github.ye17186.myhelper.core.oss.type.OssType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye17186
 * @since 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.oss")
public class MhMinioProperties {

    private OssType type = OssType.MINIO;

    /**
     * endpoint，一般用于内网
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

    /**
     * 代理endpoint，一般用于外网
     */
    protected String proxyEndpoint;
}
