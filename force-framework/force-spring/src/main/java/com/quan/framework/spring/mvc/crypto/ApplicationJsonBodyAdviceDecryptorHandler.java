package com.quan.framework.spring.mvc.crypto;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * JSON 类型请求解密处理器
 *
 * @author Force-oneself
 * @date 2023-08-21
 */
@Component
public class ApplicationJsonBodyAdviceDecryptorHandler implements BodyAdviceDecryptorHandler {

    private final ObjectMapper objectMapper;
    private final List<AdviceDecryptor> decryptorList;

    public ApplicationJsonBodyAdviceDecryptorHandler(ObjectMapper objectMapper, List<AdviceDecryptor> decryptorList) {
        this.objectMapper = objectMapper;
        this.decryptorList = decryptorList;
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
                throw new RuntimeException(e);
            }
            Map.Entry<String, Object> msgEncrypt = dataMap.entrySet().stream().iterator().next();
            if (msgEncrypt != null) {
                AtomicReference<byte[]> decryptBytes = new AtomicReference<>(String.valueOf(msgEncrypt.getValue()).getBytes(StandardCharsets.UTF_8));
                decryptorList.stream()
                        .filter(d -> d.support(holder))
                        .forEach(d -> decryptBytes.getAndUpdate(d::decrypt));
                return decryptBytes.get();
            }
            return ciphertext;
        };
    }
}
