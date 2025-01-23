package com.quan.oss.support.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.quan.oss.OssClient;

import java.io.InputStream;

/**
 * 阿里云 OSS 客户端
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
public class AliYunOssClient implements OssClient {

    /**
     * 阿里云配置
     */
    private final AliYunOssProperties properties;

    /**
     * 阿里云 SDK OSS 客户端
     */
    private final OSS ossClient;


    public AliYunOssClient(AliYunOssProperties properties) {
        this.properties = properties;
        this.ossClient = new OSSClientBuilder()
                .build(this.properties.getEndpoint(), this.properties.getAccessKey(), this.properties.getSecretKey());
    }

    @Override
    public String bucket() {
        return properties.getBucketName();
    }

    @Override
    public String upload(String bucket, String objectName, InputStream inputStream) {

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, objectName, inputStream);

        // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        // 上传
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        return result.getResponse().getUri();

    }

    @Override
    public void delete(String bucket, String objectName) {
        ossClient.deleteObject(bucket, objectName);
    }

    @Override
    public InputStream download(String bucket, String objectName) {
        return ossClient.getObject(bucket, objectName).getObjectContent();
    }

    @Override
    public void close() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}
