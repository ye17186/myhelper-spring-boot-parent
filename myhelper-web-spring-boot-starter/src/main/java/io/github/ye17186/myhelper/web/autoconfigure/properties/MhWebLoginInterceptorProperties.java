package io.github.ye17186.myhelper.web.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author ye17186
 * @since 2023-03-19
 */
@Setter
@Getter
public class MhWebLoginInterceptorProperties {

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
