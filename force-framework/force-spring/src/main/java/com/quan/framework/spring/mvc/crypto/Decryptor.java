package com.quan.framework.spring.mvc.crypto;

/**
 * 解密器
 *
 * @author Force-oneself
 * @date 2023-08-17
 */
public interface Decryptor {

    /**
     * 解密
     *
     * @param ciphertext 密文
     * @return 明文
     */
    byte[] decrypt(byte[] ciphertext);
}
