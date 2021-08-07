package com.quan.wx.open.api.impl;

import com.quan.wx.open.api.MultiWxOpenConfigStorageFactory;
import com.quan.wx.open.api.OpenConfigStorageAdder;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Force-oneself 支持多数据源多平台配置
 * @Description MultiSourceWxOpenConfigStorageFactory.java
 * @date 2021-08-06
 */
public class MultiSourceWxOpenConfigStorage implements MultiWxOpenConfigStorageFactory {

    private final List<? extends OpenConfigStorageAdder> adders;

    public MultiSourceWxOpenConfigStorage(List<OpenConfigStorageAdder> adders) {
        this.adders = adders;
    }

    @Override
    public void endow(List<WxOpenConfigStorage> configStorages) {
        if (CollectionUtils.isEmpty(adders)) {
            return;
        }
        adders.forEach(adder -> adder.accept(configStorages));
    }
}
