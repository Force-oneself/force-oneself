package com.quan.framework.spring.mvc.crypto.decrypt;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.lang.NonNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Force-oneself
 * @date 2023-08-29
 */
public class DecryptHttpInputMessage implements HttpInputMessage {

    private final byte[] decryptedBody;

    private final HttpInputMessage inputMessage;

    public DecryptHttpInputMessage(byte[] decryptedBody, HttpInputMessage inputMessage) {
        this.decryptedBody = decryptedBody;
        this.inputMessage = inputMessage;
    }

    @Override
    @NonNull
    public InputStream getBody() {
        return new ByteArrayInputStream(decryptedBody);
    }

    @Override
    @NonNull
    public HttpHeaders getHeaders() {
        return inputMessage.getHeaders();
    }
}
