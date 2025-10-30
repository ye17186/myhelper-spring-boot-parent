package io.github.ye17186.myhelper.token.autoconfigure;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import io.github.ye17186.myhelper.token.MhTokenService;
import io.github.ye17186.myhelper.token.customizer.SaTokenCustomize;
import io.github.ye17186.myhelper.token.properties.TokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye17186
 * @since 2023-03-03
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(TokenProperties.class)
public class MhTokenAutoConfiguration {

    @Autowired
    TokenProperties properties;

    @Bean
    public SaTokenCustomize saTokenCustomize(SaTokenConfig config) {

        return new SaTokenCustomize(config, properties);
    }

    @Bean
    public MhTokenService mhTokenService() {

        log.info("【MyHelper】【Token】初始化成功.");
        return new MhTokenService();
    }

    @Configuration
    @ConditionalOnProperty(name = "spring.my-helper.token.enable-annotation-permission", havingValue = "true",
            matchIfMissing = true)
    public static class MhPermissionInterceptor implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {

            registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
        }
    }
}
