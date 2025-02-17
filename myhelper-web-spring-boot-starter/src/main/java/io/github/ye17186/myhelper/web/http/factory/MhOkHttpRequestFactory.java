package io.github.ye17186.myhelper.web.http.factory;

import io.github.ye17186.myhelper.web.http.interceptor.OkHttpTimeoutInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

/**
 * @author yemeng20
 */
public class MhOkHttpRequestFactory extends OkHttp3ClientHttpRequestFactory {

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .addInterceptor(new OkHttpTimeoutInterceptor())
            .build();

    public MhOkHttpRequestFactory() {
        super(CLIENT);
    }
}
