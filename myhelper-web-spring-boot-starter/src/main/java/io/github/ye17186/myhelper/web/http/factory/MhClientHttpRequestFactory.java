package io.github.ye17186.myhelper.web.http.factory;

import io.github.ye17186.myhelper.web.http.HttpClientHolder;
import io.github.ye17186.myhelper.web.http.HttpClientProperties;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * @author ye17186
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
        if (connection instanceof HttpsURLConnection) {
            prepareHttps((HttpsURLConnection) connection);
        }
    }

    private void prepareHttps(HttpsURLConnection connection) {

        connection.setHostnameVerifier((s, sslSession) -> true);
        try {
            connection.setSSLSocketFactory(createSSLSocketFactory());
        } catch (Exception ignore) {
        }
    }

    private SSLSocketFactory createSSLSocketFactory() throws Exception {

        SSLContext context = SSLContext.getInstance("SSL");
        context.init(null, new TrustManager[]{new SkipX509TrustManager()}, new SecureRandom());
        return context.getSocketFactory();
    }

    private static class SkipX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
