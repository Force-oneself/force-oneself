package com.quan.framework.spring.mvc.crypto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-18
 */
@ConfigurationProperties(prefix = "force.servlet.crypto")
public class CryptoProperties {


    private String defaultDecryptor;

    private List<DecryptorProperties> decryptors;


    public String getDefaultDecryptor() {
        return defaultDecryptor;
    }

    public void setDefaultDecryptor(String defaultDecryptor) {
        this.defaultDecryptor = defaultDecryptor;
    }

    public List<DecryptorProperties> getDecryptors() {
        return decryptors;
    }

    public void setDecryptors(List<DecryptorProperties> decryptors) {
        this.decryptors = decryptors;
    }

    public static class DecryptorProperties {
        private String algorithm;
        private Class<?> algClass;
    }
}
