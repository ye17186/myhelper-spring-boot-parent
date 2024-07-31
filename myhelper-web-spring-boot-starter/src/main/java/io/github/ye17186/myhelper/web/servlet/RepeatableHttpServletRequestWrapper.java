package io.github.ye17186.myhelper.web.servlet;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author ye17186
 */
public class RepeatableHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public RepeatableHttpServletRequestWrapper(HttpServletRequest request) throws IOException {

        super(request);
        if (ServletFileUpload.isMultipartContent(request)) {
            this.body = null;
            return;
        }

        BufferedReader reader = request.getReader();
        try (StringWriter writer = new StringWriter()) {
            int read;
            char[] buf = new char[1024 * 8];
            while ((read = reader.read(buf)) != -1) {
                writer.write(buf, 0, read);
            }
            this.body = writer.getBuffer().toString();
        }
    }

    public String getRequestBody() {

        return this.body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
