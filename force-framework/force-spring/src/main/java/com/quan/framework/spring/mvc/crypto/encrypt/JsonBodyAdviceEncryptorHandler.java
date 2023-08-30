package com.quan.framework.spring.mvc.crypto.encrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.framework.spring.mvc.crypto.exception.CryptoException;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @date 2023-08-31
 */
public class JsonBodyAdviceEncryptorHandler implements BodyAdviceEncryptorHandler {

    private final ObjectMapper objectMapper;
    private final EncryptHandler handler;

    public JsonBodyAdviceEncryptorHandler(ObjectMapper objectMapper, EncryptHandler handler) {
        this.objectMapper = objectMapper;
        this.handler = handler;
    }

    @Override
    public boolean support(EncryptAdviceHolder holder) {
        return MediaType.APPLICATION_JSON.equals(holder.getSelectedContentType())
                || MediaType.APPLICATION_JSON_UTF8.equals(holder.getSelectedContentType());
    }

    @Override
    public Object encrypt(EncryptAdviceHolder holder) {
        try {
            byte[] encrypt = handler.encrypt(holder, objectMapper.writeValueAsBytes(holder.getBody()));
            return new String(encrypt, StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new CryptoException("加密异常", e);
        }
    }
}
