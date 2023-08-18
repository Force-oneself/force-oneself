package com.quan.boot.mvc.repeat;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author Force-oneself
 * @date 2021-08-24
 */
public class RepeatableRequestWrapper extends HttpServletRequestWrapper implements RequestRepeatable {

    protected byte[] body;

    public RepeatableRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        boolean notWrapper = super.getHeader(HttpHeaders.CONTENT_TYPE) == null
                || super.getHeader(HttpHeaders.CONTENT_TYPE)
                .startsWith(MediaType.MULTIPART_FORM_DATA_VALUE);
        if (notWrapper) {
            return super.getInputStream();
        }

        this.initBody();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }

    protected void initBody() throws IOException {
        if (body != null) {
            return;
        }
        try (StringWriter writer = new StringWriter();
             BufferedReader reader = this.getRequest().getReader()) {
            int read;
            char[] buf = new char[1024 * 8];
            while ((read = reader.read(buf)) != -1) {
                writer.write(buf, 0, read);
            }
            this.body = writer.getBuffer().toString().getBytes();
        }
    }

    @Override
    public byte[] requestBody() {
        return body;
    }
}
