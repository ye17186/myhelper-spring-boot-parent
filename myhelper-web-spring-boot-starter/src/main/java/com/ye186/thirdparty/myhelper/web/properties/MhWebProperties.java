package com.ye186.thirdparty.myhelper.web.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author ye17186
 * @date 2023-03-16
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
     * 登录状态拦截器
     */
    private List<LoginInterceptorProperties> loginInterceptors;

    @Setter
    @Getter
    public static class LoginInterceptorProperties {

        /**
         * 是否启用
         */
        private boolean enabled = true;

        /**
         * 登录类型（账号体系）
         */
        private String loginType = "default";

        /**
         * 用户信息缓存Bean ID
         */
        @NotEmpty
        private String userRef;


        /**
         * 匹配路径
         */
        private String[] includes = new String[]{"/**"};

        /**
         * 排查路径
         */
        private String[] excludes = new String[]{"/login", "/doc.html", "/webjars/**", "/v3/api-docs/**"};
    }
}
