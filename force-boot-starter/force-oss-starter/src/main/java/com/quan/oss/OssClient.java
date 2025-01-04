package com.quan.oss;

import java.io.InputStream;

/**
 * OSS 客户端
 * 抽象OSS的客户端，提供基本的OSS操作方法，包括上传、下载、删除等。
 *
 * @author Force-oneself
 * @date 2024-12-29
 */
public interface OssClient extends AutoCloseable {

    /**
     * 获取桶名称
     *
     * @return 桶
     */
    default String bucket() {
        return null;
    }

    /**
     * 上传
     *
     * @param inputStream 文件流
     * @param objectName  对象Key
     * @param bucket      桶
     */
    String upload(String bucket, String objectName, InputStream inputStream);

    /**
     * 删除
     *
     * @param bucket     桶
     * @param objectName 对象Key
     */
    void delete(String bucket, String objectName);

    /**
     * 下载
     *
     * @param bucket     桶
     * @param objectName 对象Key
     * @return 文件流
     */
    InputStream download(String bucket, String objectName);
}
