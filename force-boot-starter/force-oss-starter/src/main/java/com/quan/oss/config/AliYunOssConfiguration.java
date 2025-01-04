package com.quan.oss.config;

import com.aliyun.oss.OSS;
import com.quan.oss.OssClient;
import com.quan.oss.support.aliyun.AliYunOssClient;
import com.quan.oss.support.aliyun.AliYunOssProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Description
 *
 * @author Force-oneself
 * @date 2025-01-01
 */
@ConditionalOnClass(OSS.class)
@EnableConfigurationProperties(AliYunOssProperties.class)
public class AliYunOssConfiguration {


    @Bean
    public OssClient aliYunOssClient(AliYunOssProperties properties) {
        return new AliYunOssClient(properties);
    }

}
