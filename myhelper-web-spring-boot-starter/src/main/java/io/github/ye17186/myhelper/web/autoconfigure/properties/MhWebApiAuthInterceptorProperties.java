package io.github.ye17186.myhelper.web.autoconfigure.properties;

import io.github.ye17186.myhelper.web.consts.MhWebConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 接口鉴权
 *
 * @author ye17186
 */
@Setter
@Getter
public class MhWebApiAuthInterceptorProperties {

    /**
     * 启用API接口鉴权
     */
    private boolean enabled = false;

    /**
     * Header参数前缀
     */
    private String headerPrefix = MhWebConstants.MH_PREFIX;

    /**
     * 签名秘钥
     */
    @NotEmpty
    private String signKey;

    /**
     * 签名超时时间，单位：毫秒
     */
    private long signTimeout = 60 * 1000L;

    /**
     * 匹配路径
     */
    private String[] includes = new String[]{"/**"};

    /**
     * 排查路径
     */
    private String[] excludes = new String[]{"/doc.html", "/webjars/**", "/v3/api-docs/**", "/favicon.ico", "/error"};
}
