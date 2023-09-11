package com.quan.boot.mvc.crypto.rsa;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.quan.boot.mvc.crypto.AdviceCrypto;
import com.quan.boot.mvc.crypto.CryptoProperties;
import com.quan.boot.mvc.crypto.decrypt.DecryptAdviceHolder;
import com.quan.boot.mvc.crypto.encrypt.EncryptAdviceHolder;

/**
 * @author Force-oneself
 * @date 2023-09-08
 */
public class RSACrypto implements AdviceCrypto {

    private final CryptoProperties properties;

    public RSACrypto(CryptoProperties properties) {
        this.properties = properties;
    }

    @Override
    public byte[] decrypt(DecryptAdviceHolder holder, byte[] ciphertext) {
        return SecureUtil.rsa(properties.getRsa().getPrivateKey(), null)
                .decrypt(Base64.decode(ciphertext), KeyType.PrivateKey);
    }

    @Override
    public byte[] encrypt(EncryptAdviceHolder holder, byte[] ciphertext) {
        return SecureUtil.rsa(properties.getRsa().getPublicKey(), null)
                .encrypt(ciphertext, KeyType.PublicKey);
    }
}
