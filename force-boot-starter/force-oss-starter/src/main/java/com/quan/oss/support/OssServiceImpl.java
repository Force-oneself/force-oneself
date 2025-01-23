package com.quan.oss.support;

import com.quan.oss.*;
import com.quan.oss.exception.OssException;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * OSS 服务实现
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public class OssServiceImpl implements OssService {

    /**
     * OSS 选择器
     */
    private final OssSelector selector;

    public OssServiceImpl(OssSelector selector) {
        this.selector = selector;
    }

    @Override
    public OS upload(OssContext context) {
        OssClient ossClient = selector.select(context);
        if (Objects.isNull(ossClient)) {
            throw new OssException("OSS client not found");
        }
        String bucket = ossClient.bucket();
        if (StringUtils.isEmpty(bucket)) {
            bucket = context.bucket();
        }

        String url = ossClient.upload(bucket, context.objectName(), context.content());
        return null;
    }
}
