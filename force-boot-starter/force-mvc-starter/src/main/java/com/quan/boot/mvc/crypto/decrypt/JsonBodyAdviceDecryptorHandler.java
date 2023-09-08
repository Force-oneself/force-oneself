package com.quan.boot.mvc.crypto.decrypt;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.boot.mvc.crypto.exception.CryptoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JSON格式请求解密处理器
 * <p>application/json</p>
 * <p>application/json;charset=UTF-8</p>
 *
 * @author Force-oneself
 * @date 2023-08-21
 */
public class JsonBodyAdviceDecryptorHandler implements BodyAdviceDecryptorHandler {

    private final ObjectMapper objectMapper;
    private final DecryptHandler decryptHandler;

    public JsonBodyAdviceDecryptorHandler(ObjectMapper objectMapper, DecryptHandler decryptHandler) {
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
    public HttpInputMessage decrypt(DecryptAdviceHolder holder) throws IOException {
        byte[] ciphertext = StreamUtils.copyToByteArray(holder.getInputMessage().getBody());
        Map<String, Object> dataMap;
        try {
            dataMap = objectMapper.readValue(ciphertext, new TypeReference<LinkedHashMap<String, Object>>() {
            });
        } catch (IOException e) {
            throw new CryptoException("解析异常", e);
        }
        Map.Entry<String, Object> msgEncrypt = dataMap.entrySet().stream().iterator().next();
        if (msgEncrypt == null) {
            return holder.getInputMessage();
        }
        byte[] c = String.valueOf(msgEncrypt.getValue()).getBytes(StandardCharsets.UTF_8);
        return new DecryptHttpInputMessage(decryptHandler.decrypt(holder, c), holder.getInputMessage());
    }
}
