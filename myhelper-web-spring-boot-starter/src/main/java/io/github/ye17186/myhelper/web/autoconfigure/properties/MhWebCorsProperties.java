package io.github.ye17186.myhelper.web.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ye17186
 * @since 2023-03-16
 */
@Setter
@Getter
public class MhWebCorsProperties {

    /**
     * 启用日志切面
     */
    private boolean enabled = true;

    private String mapping;
    private List<String> origins;
    private List<String> headers;
    private List<String> methods;
    private Boolean allowCredentials;
}
