package io.github.ye17186.myhelper.oss.aotuconfigure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.github.ye17186.myhelper.oss.MhAwsService;
import io.github.ye17186.myhelper.oss.properties.MhOssProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye1718620
 * @since 2023-02-08
 */
@AutoConfiguration
@EnableConfigurationProperties(MhOssProperties.class)
public class MhOssAutoConfiguration {

    @Bean
    public MhAwsService mhMinioService(MhOssProperties properties) {

        AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");

        AwsClientBuilder.EndpointConfiguration endpoint =
                new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), "");

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(endpoint)
                .withPathStyleAccessEnabled(properties.isEnabledPathStyle())
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        return new MhAwsService(s3Client);
    }

}
