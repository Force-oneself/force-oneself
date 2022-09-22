package com.quan.wx.open.processor;

import com.quan.wx.open.api.WxOpenConfigStorageFactory;
import com.quan.wx.open.api.WxOpenServiceFactory;
import com.quan.wx.open.api.WxOpenServiceRegistry;
import com.quan.wx.open.properties.WxOpenProperties;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 初始三方平台服务的处理
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public class MultiWxOpenProcessor implements ApplicationListener<ContextRefreshedEvent> {

    private final WxOpenServiceRegistry registry;
    private final WxOpenServiceFactory serviceFactory;
    private final WxOpenProperties properties;
    private final WxOpenConfigStorageFactory<List<WxOpenConfigStorage>> configStorageFactory;

    public MultiWxOpenProcessor(WxOpenServiceRegistry registry,
                                WxOpenServiceFactory serviceFactory,
                                WxOpenProperties properties,
                                WxOpenConfigStorageFactory<List<WxOpenConfigStorage>> configStorageFactory) {
        this.registry = registry;
        this.serviceFactory = serviceFactory;
        this.properties = properties;
        this.configStorageFactory = configStorageFactory;
    }

    public void load() {
        if (!properties.isEnabled()) {
            return;
        }
        List<WxOpenConfigStorage> storages = configStorageFactory.get();
        if (CollectionUtils.isEmpty(storages)) {
            return;
        }
        storages.forEach(obj -> {
            WxOpenService service = serviceFactory.get();
            service.setWxOpenConfigStorage(obj);
            registry.register(obj.getComponentAppId(), service);
        });
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        load();
    }

    public WxOpenServiceRegistry getRegistry() {
        return registry;
    }

    public WxOpenServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public WxOpenConfigStorageFactory<List<WxOpenConfigStorage>> getConfigStorageFactory() {
        return configStorageFactory;
    }

    public WxOpenProperties getProperties() {
        return properties;
    }
}
