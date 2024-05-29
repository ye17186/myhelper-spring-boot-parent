package io.github.ye17186.myhelper.web.http;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author yemeng20
 * @date 2024/6/19
 */
public class MhClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

    @Override
    protected void prepareConnection(@NonNull HttpURLConnection connection, @NonNull String httpMethod) throws IOException {

        super.prepareConnection(connection, httpMethod);
        HttpClientProperties config = HttpClientHolder.getConfig();
        if (config != null) {
            connection.setConnectTimeout(config.getConnectTimeout());
            connection.setReadTimeout(config.getReadTimeout());
        }
    }
}
