package com.quan.oss;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public interface OssService {

    /**
     * 上传
     *
     * @param context 上下文
     * @return 对象资源
     */
    OS upload(OssContext context);
}
