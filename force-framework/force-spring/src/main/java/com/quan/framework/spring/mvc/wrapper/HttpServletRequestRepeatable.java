package com.quan.framework.spring.mvc.wrapper;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
@FunctionalInterface
public interface HttpServletRequestRepeatable {

    /**
     * 请求body
     *
     * @return 字节数组
     */
    byte[] requestBody();
}
