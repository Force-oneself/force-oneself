package com.quan.framework.spring.mvc.crypto.decrypt;

/**
 * 解密算法簇处理器,该接口完成对密文解密对对外实现
 *
 * @author Force-oneself
 * @date 2023-08-21
 */
public interface DecryptHandler {

    /**
     * 解密算法簇提供解密后字节数组
     *
     * @param holder     advice上下文
     * @param ciphertext 密文
     * @return 字节数组
     */
    byte[] decrypt(DecryptAdviceHolder holder, byte[] ciphertext);
}
