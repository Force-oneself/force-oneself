package com.quan.framework.spring.mvc.crypto.encrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.decrypt.AdviceDecryptorSupport;
import com.quan.framework.spring.mvc.crypto.exception.CryptoException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @date 2023-08-27
 */
public class DefaultEncryptHandler implements EncryptHandler {

    private final List<AdviceEncryptorSupport> customizableList;
    /**
     * 可自由切换默认实现解密器,可通过@Encrypt注解实现
     *
     * @see com.quan.framework.spring.mvc.crypto.annotation.Encrypt
     */
    private final List<AdviceEncryptor> switchableList;
    private final CryptoProperties properties;

    public DefaultEncryptHandler(List<AdviceEncryptor> encryptors, CryptoProperties properties) {
        this.customizableList = encryptors.stream()
                .filter(obj -> obj instanceof AdviceEncryptorSupport)
                .map(obj -> ((AdviceEncryptorSupport) obj))
                .collect(Collectors.toList());
        this.switchableList = encryptors.stream()
                .filter(obj -> !(obj instanceof AdviceEncryptorSupport))
                .collect(Collectors.toList());
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
                                .orElseThrow(() -> new CryptoException("not found Encryptor"))));
    }
}
