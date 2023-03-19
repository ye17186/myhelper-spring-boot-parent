package com.ye186.thirdparty.myhelper.web.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ye1718620
 * @date 2023-02-09
 */
@AutoConfiguration
public class MhCorsAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String mapping = "/**"; // 所有请求，也可配置成特定请求，如/api/**
        String origins = "*"; // 所有来源，也可以配置成特定的来源才允许跨域，如http://www.xxxx.com
        String methods = "*"; // 所有方法，GET、POST、PUT等
        registry.addMapping(mapping).allowedOrigins(origins).allowedMethods(methods);
    }
}
