package com.quan.framework.spring.mvc.crypto.decrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.exception.DecryptException;

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
        byte[] decrypt = customizableAdviceDecryptors.stream()
                .filter(d -> d.support(holder))
                .findFirst()
                .map(d -> d.decryptor(holder, ciphertext))
                .orElse(null);
        if (decrypt == null) {
            // 可自动切换解密器
            decrypt = switchableAdviceDecryptors.stream()
                    .filter(d -> d.getClass().getName().equals(properties.getDefaultDecryptor()) && d.support(holder))
                    .findFirst()
                    .map(d -> d.decryptor(holder, ciphertext))
                    .orElseThrow(() -> new DecryptException("not found Decryptor"));
        }
        return decrypt;
    }

}
