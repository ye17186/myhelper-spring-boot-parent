package io.github.ye17186.myhelper.token.customizer;

import cn.dev33.satoken.config.SaTokenConfig;
import io.github.ye17186.myhelper.token.properties.TokenProperties;

/**
 * @author ye17186
 * @since 2023-03-03
 */
public class SaTokenCustomize {

    private final SaTokenConfig config;
    private final TokenProperties properties;

    public SaTokenCustomize(SaTokenConfig config, TokenProperties properties) {

        this.config = config;
        this.properties = properties;
        customize();
    }

    private void customize() {

        config.setTokenName(properties.getTokenName());
        config.setTokenStyle(properties.getStyle().getCode());
        config.setTimeout(properties.getTimeout());
        config.setActiveTimeout(properties.getActiveTimeout());
        config.setIsReadBody(false);
        config.setIsReadCookie(false);
        config.setIsPrint(false);
    }
}
