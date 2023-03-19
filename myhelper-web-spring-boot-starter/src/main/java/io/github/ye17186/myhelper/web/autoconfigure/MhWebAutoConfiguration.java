package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.web.advice.MhExceptionHandlerAdvice;
import io.github.ye17186.myhelper.web.aspect.log.SysLogAspect;
import io.github.ye17186.myhelper.web.filter.MhRequestContextFilter;
import io.github.ye17186.myhelper.web.filter.RequestLogService;
import io.github.ye17186.myhelper.web.properties.MhWebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author ye1718620
 * @since 2023-02-09
 */
@AutoConfiguration
@Import({MhCorsAutoConfiguration.class, MhExceptionHandlerAdvice.class, MhInterceptorAutoConfiguration.class})
@EnableConfigurationProperties(MhWebProperties.class)
public class MhWebAutoConfiguration {

    @Bean
    public MhRequestContextFilter mhRequestContextFilter(@Autowired(required = false) RequestLogService service) {

        return new MhRequestContextFilter(service);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.enable-log-aspect", havingValue = "true",
            matchIfMissing = true)
    public SysLogAspect sysLogAspect() {

        return new SysLogAspect();
    }
}
