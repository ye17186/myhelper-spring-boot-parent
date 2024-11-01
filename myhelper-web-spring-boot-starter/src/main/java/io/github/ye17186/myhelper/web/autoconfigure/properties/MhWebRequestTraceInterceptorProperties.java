package io.github.ye17186.myhelper.web.autoconfigure.properties;

import io.github.ye17186.myhelper.web.consts.MhWebConstants;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yemeng20
 */
@Setter
@Getter
public class MhWebRequestTraceInterceptorProperties {

    /**
     * 启用RequestTrace，默认开启
     */
    private boolean enabled = true;

    /**
     * Header参数前缀
     */
    private String headerPrefix = MhWebConstants.MH_PREFIX;

    /**
     * 是否启用http请求日志记录
     */
    private boolean requestLogEnabled = true;
}
