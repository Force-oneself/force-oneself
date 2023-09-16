package com.quan.demo.oss;

/**
 * 对象存储抽象接口封装
 *
 * @author Force-oneself
 * @date 2023-01-13
 */
public interface ObjectStorage {

    /**
     * 上传
     *
     * @param uploader oss对象资源
     */
    void upload(FileResource uploader);

    /**
     * 删除
     *
     * @param resource oss对象资源
     */
    void remove(ObjectResource resource);

    /**
     * 存在
     *
     * @param resource oss对象资源
     * @return 成功
     */
    boolean exist(ObjectResource resource);

    /**
     * 下载
     *
     * @param resource oss对象资源
     * @return 文件资源
     */
    FileResource download(ObjectResource resource);
}
