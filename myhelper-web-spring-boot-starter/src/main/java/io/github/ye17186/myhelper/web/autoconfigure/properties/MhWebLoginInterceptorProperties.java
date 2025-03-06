package io.github.ye17186.myhelper.web.autoconfigure.properties;

import io.github.ye17186.myhelper.core.web.context.user.DefaultContextUser;
import io.github.ye17186.myhelper.core.web.context.user.MhContextUser;
import lombok.Getter;
import lombok.Setter;

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

    private Class<? extends MhContextUser> userType = DefaultContextUser.class;

    /**
     * 匹配路径
     */
    private String[] includes = new String[]{"/**"};

    /**
     * 排查路径
     */
    private String[] excludes = new String[]{"/doc.html", "/webjars/**", "/v3/api-docs/**", "/favicon.ico", "/error"};
}
