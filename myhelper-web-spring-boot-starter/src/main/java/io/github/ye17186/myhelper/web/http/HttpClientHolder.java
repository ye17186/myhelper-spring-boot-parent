package io.github.ye17186.myhelper.web.http;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * HTTP客户端配置
 *
 * @author ye17186
 * @date 2024/6/19
 */
public class HttpClientHolder {

    private static final ThreadLocal<HttpClientProperties> TIMEOUT_HOLDER = new TransmittableThreadLocal<>();

    /**
     * 设置当前登录用户的信息
     *
     * @param connectTimeout 连接超时时间
     * @param readTimeout    读取超时时间
     */
    public static void setConfig(Integer connectTimeout, Integer readTimeout) {

        HttpClientProperties config = new HttpClientProperties();
        config.setConnectTimeout(connectTimeout);
        config.setReadTimeout(readTimeout);
        TIMEOUT_HOLDER.set(config);
    }

    public static HttpClientProperties getConfig() {

        return TIMEOUT_HOLDER.get();
    }

    public static void clear() {

        TIMEOUT_HOLDER.remove();
    }
}
