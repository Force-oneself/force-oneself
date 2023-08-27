package com.quan.framework.spring.mvc.crypto.encrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.exception.DecryptException;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-27
 */
public class DefaultEncryptHandler implements EncryptHandler {

    private final List<CustomizableAdviceEncryptor> customizableList;
    /**
     * 可自由切换默认实现解密器,可通过@Encrypt注解实现
     *
     * @see com.quan.framework.spring.mvc.crypto.annotation.Encrypt
     */
    private final List<SwitchableAdviceEncryptor> switchableList;
    private final CryptoProperties properties;

    public DefaultEncryptHandler(List<CustomizableAdviceEncryptor> customizableList,
                                 List<SwitchableAdviceEncryptor> switchableList,
                                 CryptoProperties properties) {
        this.customizableList = customizableList;
        this.switchableList = switchableList;
        this.properties = properties;
    }

    @Override
    public byte[] encrypt(EncryptAdviceHolder holder, byte[] text) {
        // 自定义加密器
        return customizableList.stream()
                .filter(d -> d.support(holder))
                .findFirst()
                .map(d -> d.decryptor(holder, text))
                .orElseGet(() -> switchableList.stream()
                        .filter(d -> d.getClass().getName().equals(properties.getDefaultDecryptor()))
                        .findFirst()
                        .map(d -> d.decryptor(holder, text))
                        .orElseGet(() -> switchableList.stream()
                                .findFirst()
                                .map(d -> d.decryptor(holder, text))
                                .orElseThrow(() -> new DecryptException("not found Encryptor"))));
    }
}
