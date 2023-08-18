package com.quan.boot.mvc.repeat;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
@FunctionalInterface
public interface RequestRepeatable {

    /**
     * 请求body
     *
     * @return 字节数组
     */
    byte[] requestBody();
}
