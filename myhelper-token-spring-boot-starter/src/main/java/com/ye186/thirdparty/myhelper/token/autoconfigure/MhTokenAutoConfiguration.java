package com.ye186.thirdparty.myhelper.token.autoconfigure;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import com.ye186.thirdparty.myhelper.token.MhTokenService;
import com.ye186.thirdparty.myhelper.token.customizer.SaTokenCustomize;
import com.ye186.thirdparty.myhelper.token.properties.TokenProperties;
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
 * @date 2023-03-03
 */
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
