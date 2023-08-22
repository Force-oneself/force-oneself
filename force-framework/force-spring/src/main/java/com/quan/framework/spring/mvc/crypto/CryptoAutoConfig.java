package com.quan.framework.spring.mvc.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.framework.spring.mvc.crypto.decrypt.*;
import com.quan.framework.spring.mvc.crypto.encrypt.EncryptResponseBodyAdvice;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        public DecryptHandler decryptHandler(List<CustomizableAdviceDecryptor> customizableAdviceDecryptors,
                                             List<SwitchableAdviceDecryptor> switchableAdviceDecryptors,
                                             CryptoProperties properties) {
            return new DefaultDecryptHandler(customizableAdviceDecryptors, switchableAdviceDecryptors, properties);
        }

        @Bean
        public BodyAdviceDecryptorHandler json(ObjectMapper objectMapper, DecryptHandler decryptHandler) {
            return new ApplicationJsonBodyAdviceDecryptorHandler(objectMapper, decryptHandler);
        }

        @Bean
        public CustomizableAdviceDecryptor rsa() {
            return new RSADecryptor();
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

    }
}
