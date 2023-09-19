package com.quan.wx.open.api.impl;

import com.quan.wx.open.processor.MultiWxOpenProcessor;
import com.quan.wx.open.api.MultiWxOpenService;
import me.chanjar.weixin.open.api.WxOpenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持多平台绑定接口服务
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public class MultiWxOpenServiceImpl implements MultiWxOpenService {

    private final Logger log = LoggerFactory.getLogger(MultiWxOpenServiceImpl.class);
    private final MultiWxOpenProcessor processor;

    public MultiWxOpenServiceImpl(MultiWxOpenProcessor processor) {
        this.processor = processor;
    }

    @Override
    public WxOpenService router(String name) {
        if (!processor.getProperties().isEnabled()) {
            log.error("多平台配置已关闭");
            throw new RuntimeException("多平台配置已关闭");
        }
        WxOpenService service = processor.getRegistry().get(name);
        log.info("已路由appId:{}", service.getWxOpenConfigStorage().getComponentAppId());
        return service;
    }

    @Override
    public void register(String name, WxOpenService service) {
        processor.getRegistry().register(name, service);
    }

    @Override
    public Boolean refresh() {
        processor.getRegistry().clear();
        processor.load();
        return true;
    }

    @Override
    public WxOpenService remove(String name) {
        return processor.getRegistry().remove(name);
    }
}
