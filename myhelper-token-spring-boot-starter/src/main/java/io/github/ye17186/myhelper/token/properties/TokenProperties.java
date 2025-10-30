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
     * TOKEN有效期（默认7天，单：位秒）
     */
    private long timeout = 7 * 24 * 3600;

    /**
     * TOKEN最低活跃频率（单位：秒，-1 = 不限制）
     */
    private long activeTimeout = -1L;

    /**
     * 是否自动续期
     */
    private boolean antoRenew = true;

    /**
     * 启用权限注解
     */
    private boolean enableAnnotationPermission = true;
}
