package io.github.ye17186.myhelper.web.autoconfigure;

import io.github.ye17186.myhelper.web.advice.handler.MhApiAdviceHandler;
import io.github.ye17186.myhelper.web.advice.service.DefaultSysLogService;
import io.github.ye17186.myhelper.web.advice.service.SysLogService;
import io.github.ye17186.myhelper.web.aspect.log.SysLogAspect;
import io.github.ye17186.myhelper.web.autoconfigure.properties.MhWebProperties;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author ye17186
 * @since 2023-02-09
 */
@ConditionalOnProperty(name = "spring.my-helper.web.api-advice.enabled", havingValue = "true", matchIfMissing = true)
@AutoConfiguration
public class MhWebApiAdviceAutoConfiguration {

    @Autowired
    private MhWebProperties properties;

    @Bean
    @ConditionalOnMissingBean(SysLogService.class)
    public SysLogService mhSysLogService() {

        return new DefaultSysLogService();
    }

    @Bean
    public SysLogAspect sysLogAspect(@Autowired SysLogService service) {

        return new SysLogAspect(properties.getApiAdvice(), service);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.my-helper.web.api-advice.log-enabled", havingValue = "true", matchIfMissing = true)
    public DefaultPointcutAdvisor apiPointcutAdvisor(@Autowired SysLogService service) {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(properties.getApiAdvice().getExpression());

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(pointcut);
        defaultPointcutAdvisor.setAdvice(new MhApiAdviceHandler(properties.getApiAdvice(), service));
        return defaultPointcutAdvisor;
    }
}
