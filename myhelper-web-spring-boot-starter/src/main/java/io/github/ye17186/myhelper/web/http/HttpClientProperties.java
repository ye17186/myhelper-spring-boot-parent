package io.github.ye17186.myhelper.web.http;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ye17186
 * @date 2024/6/19
 */
@Setter
@Getter
public class HttpClientProperties implements Serializable {

    /**
     * 连接超时时间，毫秒
     */
    private Integer connectTimeout;

    /**
     * 读取超时时间，毫秒
     */
    private Integer readTimeout;
}
