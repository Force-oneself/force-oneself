package com.quan.framework.spring.mvc.crypto.decrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.exception.DecryptException;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-22
 */
public class DefaultDecryptor implements AdviceDecryptor {

    private final List<AdviceDecryptor> decryptorList;
    private final CryptoProperties properties;

    public DefaultDecryptor(List<AdviceDecryptor> decryptorList, CryptoProperties properties) {
        this.decryptorList = decryptorList;
        this.properties = properties;
    }

    @Override
    public boolean support(DecryptAdviceHolder holder) {
        return true;
    }

    @Override
    public byte[] decryptor(DecryptAdviceHolder holder, byte[] ciphertext) {
        return decryptorList.stream()
                .filter(d -> d.getClass().toString().equals(properties.getDefaultDecryptor()))
                .findFirst()
                .map(d -> d.decryptor(holder, ciphertext))
                .orElseThrow(() -> new DecryptException("not found Decryptor"));
    }
}
