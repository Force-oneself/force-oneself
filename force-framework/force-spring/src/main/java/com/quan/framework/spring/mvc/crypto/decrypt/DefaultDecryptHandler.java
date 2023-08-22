package com.quan.framework.spring.mvc.crypto.decrypt;

import com.quan.framework.spring.mvc.crypto.CryptoProperties;
import com.quan.framework.spring.mvc.crypto.exception.DecryptException;

import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-08-22
 */
public class DefaultDecryptHandler implements DecryptHandler {

    private final List<AdviceDecryptor> customerDecryptorList;
    private final AdviceDecryptor defaultDecryptor;

    public DefaultDecryptHandler(List<AdviceDecryptor> customerDecryptorList, CryptoProperties properties) {
        this.customerDecryptorList = customerDecryptorList;
        this.defaultDecryptor = new DefaultDecryptor(customerDecryptorList, properties);

    }

    @Override
    public byte[] decrypt(DecryptAdviceHolder holder, byte[] ciphertext) {
       return customerDecryptorList.stream()
               .filter(d-> d.support(holder))
               .findFirst()
               .map(d-> d.decryptor(holder,ciphertext))
               .orElseGet(() -> defaultDecryptor.decryptor(holder, ciphertext));
    }
}
