package io.github.ye17186.myhelper.minio.autoconfigure;

import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.minio.MhMinioService;
import io.github.ye17186.myhelper.minio.properties.MhMinioProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye17186
 * @since 2023-02-08
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MhMinioProperties.class)
public class MhMinioAutoConfiguration {

    @Bean
    public MhMinioService mhMinioService(MhMinioProperties properties) {

        MinioClient client = MinioClient.builder()
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .endpoint(properties.getEndpoint())
                .build();

        MinioClient proxyClient = null;
        if (StringUtils.isNotEmpty(properties.getProxyEndpoint())) {
            proxyClient = MinioClient.builder()
                    .credentials(properties.getAccessKey(), properties.getSecretKey())
                    .endpoint(properties.getProxyEndpoint())
                    .build();
        }

        MhMinioService service = new MhMinioService(client, proxyClient);

        log.info("【MyHelper】【Minio】对象存储服务Minio客户端注册完成. endpoint: {}", properties.getEndpoint());
        if (proxyClient != null) {
            log.info("【MyHelper】【Minio】对象存储服务Minio代理客户端注册完成. endpoint: {}", properties.getProxyEndpoint());
        }
        return service;
    }

}
