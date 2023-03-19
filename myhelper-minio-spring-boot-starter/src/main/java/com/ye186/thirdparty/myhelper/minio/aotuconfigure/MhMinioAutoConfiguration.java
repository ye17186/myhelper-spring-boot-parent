package com.ye186.thirdparty.myhelper.minio.aotuconfigure;

import com.ye186.thirdparty.myhelper.minio.MhMinioService;
import com.ye186.thirdparty.myhelper.minio.properties.MhMinioProperties;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye1718620
 * @date 2023-02-08
 */
@AutoConfiguration
@EnableConfigurationProperties(MhMinioProperties.class)
public class MhMinioAutoConfiguration {

    @Bean
    public MhMinioService mhMinioService(MhMinioProperties properties) {

        MinioClient client = MinioClient.builder()
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .endpoint(properties.getEndpoint())
                .build();
        return new MhMinioService(client);
    }

}
