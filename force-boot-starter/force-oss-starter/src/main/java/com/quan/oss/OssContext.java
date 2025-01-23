package com.quan.oss;

import java.io.InputStream;

/**
 * OSS 上下文
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public interface OssContext {

    /**
     * 用户ID
     *
     * @return 用户ID
     */
    long userId();

    /**
     * 上传内容
     *
     * @return 文件内容
     */
    InputStream content();

    /**
     * 桶
     *
     * @return 桶名称
     */
    String bucket();

    /**
     * 对象Key
     *
     * @return 对象Key
     */
    String objectName();

    /**
     * 内容长度
     */
    long contentLength();

    /**
     * 内容类型
     *
     * @return 类型
     */
    String contentType();

    /**
     * 设置附加的信息
     *
     * @param attachKey 附加属性Key
     * @param value     附加值
     */
    <T> void setAttach(String attachKey, T value);

    /**
     * 获取附加信息
     *
     * @param attachKey 附加属性Key
     * @return 附加值
     */
    <T> T attach(String attachKey);
}
