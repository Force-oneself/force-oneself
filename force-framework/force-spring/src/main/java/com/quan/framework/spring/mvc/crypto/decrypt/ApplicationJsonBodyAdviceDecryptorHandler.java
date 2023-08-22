package com.quan.framework.spring.mvc.crypto.decrypt;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.framework.spring.mvc.crypto.exception.DecryptException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JSON 类型请求解密处理器
 *
 * @author Force-oneself
 * @date 2023-08-21
 */
public class ApplicationJsonBodyAdviceDecryptorHandler implements BodyAdviceDecryptorHandler {

    private final ObjectMapper objectMapper;
    private final DecryptHandler decryptHandler;

    public ApplicationJsonBodyAdviceDecryptorHandler(ObjectMapper objectMapper, DecryptHandler decryptHandler) {
        this.objectMapper = objectMapper;
        this.decryptHandler = decryptHandler;
    }

    @Override
    public boolean support(DecryptAdviceHolder holder) {
        HttpHeaders headers = holder.getInputMessage().getHeaders();
        return MediaType.APPLICATION_JSON.equals(headers.getContentType())
                || MediaType.APPLICATION_JSON_UTF8.equals(headers.getContentType());
    }

    @Override
    public Decryptor decryptor(DecryptAdviceHolder holder) {
        return ciphertext -> {
            Map<String, Object> dataMap;
            try {
                dataMap = objectMapper.readValue(ciphertext, new TypeReference<LinkedHashMap<String, Object>>() {
                });
            } catch (IOException e) {
                throw new DecryptException("解析异常", e);
            }
            Map.Entry<String, Object> msgEncrypt = dataMap.entrySet().stream().iterator().next();
            if (msgEncrypt == null) {
                return ciphertext;
            }
            byte[] c = String.valueOf(msgEncrypt.getValue()).getBytes(StandardCharsets.UTF_8);
            return decryptHandler.decrypt(holder, c);
        };
    }
}
