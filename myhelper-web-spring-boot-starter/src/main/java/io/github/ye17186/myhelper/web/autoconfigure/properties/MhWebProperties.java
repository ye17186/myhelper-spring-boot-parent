package io.github.ye17186.myhelper.web.autoconfigure.properties;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.web")
public class MhWebProperties {

    /**
     * 启用日志切面
     */
    private boolean enableLogAspect = true;

    /**
     * 延时事件
     */
    private MhWebDelayEventProperties delayEvent = new MhWebDelayEventProperties();

    /**
     * 异步线程
     */
    private MhWebAsyncProperties async = new MhWebAsyncProperties();

    /**
     * 登录拦截器
     */
    private List<MhWebLoginInterceptorProperties> loginInterceptors = Lists.newArrayList();
}
