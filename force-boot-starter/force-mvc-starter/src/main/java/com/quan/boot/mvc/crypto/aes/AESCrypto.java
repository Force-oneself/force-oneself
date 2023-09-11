package com.quan.boot.mvc.crypto.aes;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.quan.boot.mvc.crypto.AdviceCrypto;
import com.quan.boot.mvc.crypto.CryptoProperties;
import com.quan.boot.mvc.crypto.decrypt.DecryptAdviceHolder;
import com.quan.boot.mvc.crypto.encrypt.EncryptAdviceHolder;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @date 2023-09-08
 */
public class AESCrypto implements AdviceCrypto {

    private final CryptoProperties properties;

    public AESCrypto(CryptoProperties properties) {
        this.properties = properties;
    }

    @Override
    public byte[] decrypt(DecryptAdviceHolder holder, byte[] ciphertext) {
        return SecureUtil.aes(Base64.decode(properties.getAes().getPrivateKey())).decrypt(ciphertext);
    }

    @Override
    public byte[] encrypt(EncryptAdviceHolder holder, byte[] ciphertext) {
        return SecureUtil.aes(Base64.decode(properties.getAes().getPrivateKey())).encrypt(ciphertext);
    }
}
