package com.ye186.thirdparty.myhelper.web.autoconfigure;

import com.ye186.thirdparty.myhelper.core.utils.CollectionUtils;
import com.ye186.thirdparty.myhelper.token.MhTokenService;
import com.ye186.thirdparty.myhelper.web.interceptor.login.MhLoginInterceptor;
import com.ye186.thirdparty.myhelper.web.properties.MhWebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye17186
 * @date 2023-03-16
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
