package com.quan.wx.open.api.impl;

import com.quan.wx.open.api.WxOpenServiceRegistry;
import me.chanjar.weixin.open.api.WxOpenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多平台配置注册中心
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public class MultiWxOpenServiceRegistry implements WxOpenServiceRegistry {

    private final Logger log = LoggerFactory.getLogger(MultiWxOpenServiceRegistry.class);
    private final Map<String, WxOpenService> serviceCache = new ConcurrentHashMap<>(512);

    @Override
    public void register(String name, WxOpenService service) {
        if (serviceCache.containsKey(name)) {
            log.warn("{} service 已存在", name);
        }
        serviceCache.put(name, service);
    }

    @Override
    public WxOpenService get(String name) {
        if (!serviceCache.containsKey(name)) {
            throw new RuntimeException("不存在该WxOpenService请检查:" + name);
        }
        return serviceCache.get(name);
    }

    @Override
    public Set<WxOpenService> getAll() {
        return new HashSet<>(serviceCache.values());
    }

    @Override
    public void clear() {
        serviceCache.clear();
    }

    @Override
    public WxOpenService remove(String name) {
        return serviceCache.remove(name);
    }
}
