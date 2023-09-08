package com.quan.boot.mvc.crypto;

import com.quan.boot.mvc.constant.PropConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
