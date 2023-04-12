package io.github.ye17186.myhelper.web.autoconfigure.properties;

import com.google.common.collect.Lists;
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

    private String mapping = "/**";
    private List<String> origins = Lists.newArrayList("*");
    private List<String> headers = Lists.newArrayList("*");
    private List<String> methods = Lists.newArrayList("*");
    private Boolean allowCredentials;
}
