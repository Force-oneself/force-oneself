package com.quan.boot.mvc.crypto;

import com.quan.boot.mvc.constant.PropConstant;
import com.quan.boot.mvc.crypto.aes.AESProperties;
import com.quan.boot.mvc.crypto.rsa.RSAProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-18
 */
@ConfigurationProperties(prefix = PropConstant.CRYPTO)
public class CryptoProperties {

    /**
     * 解密器，存在多个时指定
     */
    private String decryptor;
    /**
     * 加密器，存在多个时指定
     */
    private String encryptor;

    /**
     * RSA
     */
    @NestedConfigurationProperty
    private RSAProperties rsa;

    /**
     * AES
     */
    @NestedConfigurationProperty
    private AESProperties aes;

    public String getDecryptor() {
        return decryptor;
    }

    public void setDecryptor(String decryptor) {
        this.decryptor = decryptor;
    }

    public String getEncryptor() {
        return encryptor;
    }

    public void setEncryptor(String encryptor) {
        this.encryptor = encryptor;
    }

    public RSAProperties getRsa() {
        return rsa;
    }

    public void setRsa(RSAProperties rsa) {
        this.rsa = rsa;
    }

    public AESProperties getAes() {
        return aes;
    }

    public void setAes(AESProperties aes) {
        this.aes = aes;
    }
}
