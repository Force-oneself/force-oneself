package com.quan.oss;

/**
 * OSS 选择器
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public interface OssSelector {

    /**
     * 通过上下文关系, 选择对应的OSS客户端
     *
     * @param context 上下文
     * @return OSS客户端
     */
    OssClient select(OssContext context);
}
