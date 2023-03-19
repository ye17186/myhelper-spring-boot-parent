package com.ye186.thirdparty.myhelper.mybatis.aotuconfigure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ye186.thirdparty.myhelper.mybatis.customizer.MhMybatisPlusPropertiesCustomizer;
import com.ye186.thirdparty.myhelper.mybatis.aotuconfigure.properties.MhMybatisProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author ye1718620
 * @date 2023-02-08
 */
@AutoConfiguration
@EnableConfigurationProperties(MhMybatisProperties.class)
@MapperScan("${spring.my-helper.mybatis.config.mapper-scans:}")
public class MhMybatisAutoConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        // 初始化MP拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    MhMybatisPlusPropertiesCustomizer mhMybatisPlusPropertiesCustomizer(MhMybatisProperties properties) {

        return new MhMybatisPlusPropertiesCustomizer(properties);
    }
}
