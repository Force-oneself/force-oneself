package com.quan.boot.mvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.boot.mvc.crypto.CryptoProperties;
import com.quan.boot.mvc.crypto.decrypt.*;
import com.quan.boot.mvc.crypto.encrypt.*;
import com.quan.boot.mvc.crypto.rsa.RSAProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-03-03
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CryptoProperties.class)
public class CryptoAutoConfig {

    /**
     * 加密自动配置
     *
     * @author Force-oneself
     * @date 2023-08-22
     */
    @Configuration(proxyBeanMethods = false)
    @Import({DecryptRequestBodyAdvice.class})
    public static class DecryptAutoConfig {

        @Bean
        public DecryptHandler decryptHandler(List<AdviceDecryptor> decryptors, CryptoProperties properties) {
            return new DefaultDecryptHandler(decryptors, properties);
        }

        @Bean
        public JsonBodyAdviceDecryptorHandler applicationJsonBodyAdviceDecryptorHandler(
                ObjectMapper objectMapper, DecryptHandler decryptHandler) {
            return new JsonBodyAdviceDecryptorHandler(objectMapper, decryptHandler);
        }

    }


    /**
     * 解密自动配置
     *
     * @author Force-oneself
     * @date 2023-08-22
     */
    @Configuration(proxyBeanMethods = false)
    @Import({EncryptResponseBodyAdvice.class})
    public static class EncryptAutoConfig {

        @Bean
        public EncryptHandler encryptHandler(List<AdviceEncryptor> encryptors, CryptoProperties properties) {
            return new DefaultEncryptHandler(encryptors, properties);
        }

        @Bean
        public CommonReturnBodyAdviceEncryptorHandler commonReturnBodyAdviceEncryptorHandler(ObjectMapper objectMapper, EncryptHandler encryptHandler) {
            return new CommonReturnBodyAdviceEncryptorHandler(objectMapper, encryptHandler);
        }

        @Bean
        public JsonBodyAdviceEncryptorHandler jsonBodyAdviceEncryptorHandler(ObjectMapper objectMapper, EncryptHandler encryptHandler) {
            return new JsonBodyAdviceEncryptorHandler(objectMapper, encryptHandler);
        }
    }
}
