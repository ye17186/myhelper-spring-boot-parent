package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.core.utils.CollectionUtils;
import io.github.ye17186.myhelper.token.MhTokenService;
import io.github.ye17186.myhelper.web.interceptor.login.MhLoginInterceptor;
import io.github.ye17186.myhelper.web.properties.MhWebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@AutoConfiguration
public class MhInterceptorAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    MhWebProperties mhWebProperties;

    @Autowired(required = false)
    MhTokenService tokenService;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {

        if (CollectionUtils.isNotEmpty(mhWebProperties.getLoginInterceptors())) {
            mhWebProperties.getLoginInterceptors().forEach(config -> {
                MhLoginInterceptor interceptor = new MhLoginInterceptor(tokenService, config.getLoginType(),
                        config.getUserRef());

                registry.addInterceptor(interceptor)
                        .addPathPatterns(config.getIncludes())
                        .excludePathPatterns(config.getExcludes());
            });
        }
    }
}
