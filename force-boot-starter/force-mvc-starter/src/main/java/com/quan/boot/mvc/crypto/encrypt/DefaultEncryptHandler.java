package com.quan.boot.mvc.crypto.encrypt;

import com.quan.boot.mvc.crypto.annotation.Encrypt;
import com.quan.boot.mvc.crypto.CryptoProperties;
import com.quan.boot.mvc.crypto.exception.CryptoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Force-oneself
 * @date 2023-08-27
 */
public class DefaultEncryptHandler implements EncryptHandler {

    private final List<AdviceEncryptorSupport> customizable;
    /**
     * 可自由切换默认实现解密器,可通过@Encrypt注解实现
     *
     * @see Encrypt
     */
    private final Map<String, AdviceEncryptor> switchable;
    private final CryptoProperties properties;

    public DefaultEncryptHandler(List<AdviceEncryptor> encryptors, CryptoProperties properties) {
        this.customizable = encryptors.stream()
                .filter(obj -> obj instanceof AdviceEncryptorSupport)
                .map(obj -> ((AdviceEncryptorSupport) obj))
                .collect(Collectors.toList());
        this.switchable = encryptors.stream()
                .filter(obj -> !(obj instanceof AdviceEncryptorSupport))
                .collect(Collectors.toMap(obj -> obj.getClass().getName(), obj -> obj));
        this.properties = properties;
    }

    @Override
    public byte[] encrypt(EncryptAdviceHolder holder, byte[] text) {
        return customizable.stream()
                // 自定义
                .filter(d -> d.support(holder))
                .findFirst()
                .map(d -> d.encrypt(holder, text))
                // 指定加解密器
                .orElseGet(() -> Optional.ofNullable(switchable.get(properties.getEncryptor()))
                        .map(obj -> obj.encrypt(holder, text))
                        // 自动匹配第一个
                        .orElseGet(() -> switchable.entrySet()
                                .stream()
                                .findFirst()
                                .map(d -> d.getValue().encrypt(holder, text))
                                .orElseThrow(() -> new CryptoException("not found Encryptor"))));
    }
}
