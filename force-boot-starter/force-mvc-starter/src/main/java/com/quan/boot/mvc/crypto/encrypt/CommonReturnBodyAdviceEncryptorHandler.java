package com.quan.boot.mvc.crypto.encrypt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.common.bean.R;
import com.quan.boot.mvc.crypto.exception.CryptoException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Force-oneself
 * @date 2023-08-30
 */
public class CommonReturnBodyAdviceEncryptorHandler implements BodyAdviceEncryptorHandler {

    private final ObjectMapper objectMapper;
    private final EncryptHandler handler;

    public CommonReturnBodyAdviceEncryptorHandler(ObjectMapper objectMapper, EncryptHandler handler) {
        this.objectMapper = objectMapper;
        this.handler = handler;
    }

    @Override
    public boolean support(EncryptAdviceHolder holder) {
        return R.class.isAssignableFrom(holder.getBody().getClass());
    }

    @Override

    public Object encrypt(EncryptAdviceHolder holder) {
        Class<?> realReturnClass = holder.getBody().getClass();
        if (!R.class.isAssignableFrom(realReturnClass)) {
            return holder.getBody();
        }
        @SuppressWarnings("unchecked")
        R<Object> realBody = (R<Object>) holder.getBody();
        Object data = realBody.getData();
        if (Objects.isNull(data)) {
            return realBody;
        }
        try {
            byte[] encrypt = handler.encrypt(holder, objectMapper.writeValueAsBytes(data));
            realBody.setData(new String(encrypt, StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            throw new CryptoException("加密异常", e);
        }
        return realBody;
    }
}
