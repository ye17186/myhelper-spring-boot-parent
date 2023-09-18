package io.github.ye17186.myhelper.core.web.env;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ye17186
 * @date 2023-04-27
 */
@Getter
@AllArgsConstructor
public enum AppEnvEnum {

    LOCAL("local", "本地环境"),
    DEV("dev", "开发环境"),
    BETA("beta", "测试环境"),
    PROD("prod", "生产环境"),
    ;

    private final String code;
    private final String desc;
}
