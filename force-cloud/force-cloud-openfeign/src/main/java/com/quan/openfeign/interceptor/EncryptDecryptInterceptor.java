package com.quan.openfeign.interceptor;

import feign.*;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @date 2023-04-11
 */
public class EncryptDecryptInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        MethodMetadata metadata = template.methodMetadata();

        if (metadata != null && metadata.method().getAnnotation(AutoEncryptDecrypt.class) != null) {

            //加密请求参数
            byte[] requestBody = template.body();
            if (requestBody != null) {
                template.body(requestBody, StandardCharsets.UTF_8);
            }

            //解密响应内容
            Response response = executeAndDecode(template);
            if (response.body() != null) {
                byte[] responseBody = decrypt(response.body().toString().getBytes(StandardCharsets.UTF_8));
                response = response.toBuilder().body(responseBody).build();
            }
            // template.(response);x
        }
    }

    //发送请求并返回响应结果
    private Response executeAndDecode(RequestTemplate template) {
        return Response.builder()
                .body(template.body())
                .request(template.request())
                .headers(template.headers())
                .requestTemplate(template)
                .build();
    }

    //加密
    private byte[] encrypt(byte[] plaintext) {
        // TODO: 加密实现
        return plaintext;
    }

    //解密
    private byte[] decrypt(byte[] ciphertext) {
        // TODO: 解密实现
        return ciphertext;
    }
}

