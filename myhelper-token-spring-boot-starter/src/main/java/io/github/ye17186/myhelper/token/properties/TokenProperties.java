package io.github.ye17186.myhelper.token.properties;

import io.github.ye17186.myhelper.token.style.TokenStyle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye17186
 * @since 2023-03-03
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.token")
public class TokenProperties {

    /**
     * token名
     */
    private String tokenName = "MH-ACCESS-TOKEN";

    /**
     * token风格
     */
    private TokenStyle style = TokenStyle.RANDOM64;

    /**
     * TOKEN有效期（默认30天）
     */
    private long timeout = 2592000L;

    /**
     * TOKEN临时有效期（默认长期）
     */
    private long activityTimeout = -1L;

    /**
     * 启用权限注解
     */
    private boolean enableAnnotationPermission = true;
}
