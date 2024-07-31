package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebApiAuthProperties;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import io.github.ye17186.myhelper.web.interceptor.api.MhApiAuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@Slf4j
@ConditionalOnProperty(name = "spring.my-helper.web.api-auth-interceptor.enabled", havingValue = "true")
@AutoConfiguration(before = MhWebLoginInterceptorAutoConfiguration.class)
public class MhWebApiAuthInterceptorAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    MhWebProperties properties;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {

        MhWebApiAuthProperties config = properties.getApiAuthInterceptor();
        MhApiAuthInterceptor interceptor = new MhApiAuthInterceptor(config);

        registry.addInterceptor(interceptor)
                .addPathPatterns(config.getIncludes())
                .excludePathPatterns(config.getExcludes());
        log.info("【MyHelper】【Web】 注册API接口鉴权拦截器完成.");
    }
}
