package com.quan.boot.mvc.crypto.decrypt;

import com.quan.boot.mvc.crypto.CryptoProperties;
import com.quan.boot.mvc.crypto.annotation.Decrypt;
import com.quan.boot.mvc.crypto.exception.CryptoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @date 2023-08-22
 */
public class DefaultDecryptHandler implements DecryptHandler {

    /**
     * 可定义解密器
     */
    private final List<AdviceDecryptorSupport> customizable;
    /**
     * 可自由切换默认实现解密器,可通过@Decrypt注解实现
     *
     * @see Decrypt
     */
    private final Map<String, AdviceDecryptor> switchable;
    private final CryptoProperties properties;

    public DefaultDecryptHandler(List<AdviceDecryptor> decryptors, CryptoProperties properties) {
        this.customizable = decryptors.stream()
                .filter(obj -> obj instanceof AdviceDecryptorSupport)
                .map(obj -> ((AdviceDecryptorSupport) obj))
                .collect(Collectors.toList());
        this.switchable = decryptors.stream()
                .filter(obj -> !(obj instanceof AdviceDecryptorSupport))
                .collect(Collectors.toMap(obj -> obj.getClass().getName(), obj -> obj));
        this.properties = properties;
    }

    @Override
    public byte[] decrypt(DecryptAdviceHolder holder, byte[] text) {
        return customizable.stream()
                // 自定义解密器
                .filter(d -> d.support(holder))
                .findFirst()
                .map(d -> d.decrypt(holder, text))
                // 指定加解密器
                .orElseGet(() -> Optional.ofNullable(switchable.get(properties.getDecryptor()))
                        .map(obj -> obj.decrypt(holder, text))
                        // 自动匹配第一个
                        .orElseGet(() -> switchable.entrySet()
                                .stream()
                                .findFirst()
                                .map(d -> d.getValue().decrypt(holder, text))
                                .orElseThrow(() -> new CryptoException("not found Decryptor"))));
    }

}
