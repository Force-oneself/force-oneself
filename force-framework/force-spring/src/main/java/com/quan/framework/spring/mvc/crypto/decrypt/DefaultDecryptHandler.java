package com.quan.framework.spring.mvc.crypto.decrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.exception.CryptoException;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-22
 */
public class DefaultDecryptHandler implements DecryptHandler {

    /**
     * 可定义解密器
     */
    private final List<CustomizableAdviceDecryptor> customizableAdviceDecryptors;
    /**
     * 可自由切换默认实现解密器,可通过@Decrypt注解实现
     *
     * @see com.quan.framework.spring.mvc.crypto.annotation.Decrypt
     */
    private final List<SwitchableAdviceDecryptor> switchableAdviceDecryptors;
    private final CryptoProperties properties;

    public DefaultDecryptHandler(List<CustomizableAdviceDecryptor> customizableAdviceDecryptors,
                                 List<SwitchableAdviceDecryptor> switchableAdviceDecryptors,
                                 CryptoProperties properties) {
        this.customizableAdviceDecryptors = customizableAdviceDecryptors;
        this.switchableAdviceDecryptors = switchableAdviceDecryptors;
        this.properties = properties;
    }

    @Override
    public byte[] decrypt(DecryptAdviceHolder holder, byte[] ciphertext) {
        // 自定义解密器
        return customizableAdviceDecryptors.stream()
                .filter(d -> d.support(holder))
                .findFirst()
                .map(d -> d.decryptor(holder, ciphertext))
                .orElseGet(() -> switchableAdviceDecryptors.stream()
                        .filter(d -> d.getClass().getName().equals(properties.getDefaultDecryptor()))
                        .findFirst()
                        .map(d -> d.decryptor(holder, ciphertext))
                        .orElseGet(() -> switchableAdviceDecryptors.stream()
                                .findFirst()
                                .map(d -> d.decryptor(holder, ciphertext))
                                .orElseThrow(() -> new CryptoException("not found Decryptor"))));
    }

}
