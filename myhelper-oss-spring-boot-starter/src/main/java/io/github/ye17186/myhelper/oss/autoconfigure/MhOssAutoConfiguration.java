package io.github.ye17186.myhelper.oss.autoconfigure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.github.ye17186.myhelper.core.utils.StringPool;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.oss.MhAwsService;
import io.github.ye17186.myhelper.oss.properties.MhOssProperties;
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
@EnableConfigurationProperties(MhOssProperties.class)
public class MhOssAutoConfiguration {

    @Bean
    public MhAwsService mhAwsService(MhOssProperties properties) {

        AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");

        AwsClientBuilder.EndpointConfiguration endpoint =
                new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), StringPool.EMPTY);

        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endpoint)
                .withPathStyleAccessEnabled(properties.isEnabledPathStyle())
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        AmazonS3 proxyClient = null;
        if (StringUtils.isNotEmpty(properties.getProxyEndpoint())) {

            AwsClientBuilder.EndpointConfiguration proxyEndpoint =
                    new AwsClientBuilder.EndpointConfiguration(properties.getProxyEndpoint(), StringPool.EMPTY);

            proxyClient = AmazonS3ClientBuilder
                    .standard()
                    .withEndpointConfiguration(proxyEndpoint)
                    .withPathStyleAccessEnabled(properties.isEnabledPathStyle())
                    .withClientConfiguration(clientConfiguration)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();
        }

        log.info("【MyHelper】【OSS】对象存储服务OSS客户端注册完成. endpoint: {}", properties.getEndpoint());
        if (proxyClient != null) {
            log.info("【MyHelper】【OSS】对象存储服务OSS代理客户端注册完成. endpoint: {}", properties.getProxyEndpoint());
        }

        return new MhAwsService(client, proxyClient);
    }
}
