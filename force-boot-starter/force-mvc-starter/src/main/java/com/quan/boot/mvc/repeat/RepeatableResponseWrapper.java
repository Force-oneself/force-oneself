package com.quan.boot.mvc.repeat;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
public class RepeatableResponseWrapper extends HttpServletResponseWrapper implements ResponseRepeatable {

    private final ByteArrayOutputStream byteArrayOutputStream;

    private final ServletOutputStream servletOutputStream;

    public RepeatableResponseWrapper(HttpServletResponse response) {
        super(response);
        byteArrayOutputStream = new ByteArrayOutputStream();
        servletOutputStream = new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }

            @Override
            public void write(int b) throws IOException {
                response.getOutputStream().write(b);
                // 同时写入字节数组
                byteArrayOutputStream.write(b);
            }
        };
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return servletOutputStream;
    }

    public ByteArrayOutputStream byteOutputStream() {
        return byteArrayOutputStream;
    }

    @Override
    public byte[] responseBody() {
        return byteArrayOutputStream.toByteArray();
    }
}
