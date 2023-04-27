package io.github.ye17186.myhelper.knife4j.aotuconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ye17186
 * @since 2023-02-08
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.my-helper.knife4j")
public class MhKnife4jProperties {

    /**
     * 文档标题
     */
    private String title = "【My-Helper】在线接口文档";

    /**
     * 文档描述
     */
    private String description = "【My-Helper】在线接口文档";

    /**
     * 作者
     */
    private String author = "ye17186";

    /**
     * 版本
     */
    private String version = "V1.0.0";

    /**
     * 主页URL
     */
    private String url = "https://blog.ye186.com";

    /**
     * 邮箱
     */
    private String email = "ye17186@163.com";
}
