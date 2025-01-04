package com.quan.oss.support;

import com.quan.oss.OssClient;
import com.quan.oss.OssClientRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单实现 OSS 客户端注册中心
 *
 * @author Force-oneself
 * @date 2024-12-30
 */
public class SimpleOssClientRegistry implements OssClientRegistry {

    /**
     * OSSClient 缓存
     */
    private final Map<String, OssClient> clientMap;

    public SimpleOssClientRegistry() {
        clientMap = new ConcurrentHashMap<>();
    }

    @Override
    public void register(String key, OssClient client) {
        clientMap.put(key, client);
    }

    @Override
    public OssClient get(String key) {
        return clientMap.get(key);
    }

    @Override
    public void close() throws Exception {
        for (OssClient client : clientMap.values()) {
            client.close();
        }
        clientMap.clear();
    }

    @Override
    public void destroy() throws Exception {
        this.close();
    }
}
