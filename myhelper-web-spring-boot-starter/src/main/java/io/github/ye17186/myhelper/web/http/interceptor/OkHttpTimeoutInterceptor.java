package io.github.ye17186.myhelper.web.http.interceptor;

import io.github.ye17186.myhelper.web.http.HttpClientHolder;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author ye17186
 */
public class OkHttpTimeoutInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        return chain
                .withConnectTimeout(HttpClientHolder.getConfig().getConnectTimeout(), TimeUnit.MILLISECONDS)
                .withReadTimeout(HttpClientHolder.getConfig().getReadTimeout(), TimeUnit.MILLISECONDS)
                .proceed(chain.request());
    }
}
