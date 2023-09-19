package com.quan.wx.open.api.impl;

import com.quan.wx.open.api.OpenConfigStorageAdder;
import com.quan.wx.open.properties.WxOpenProperties;
import com.quan.wx.open.common.WxProperties;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置文件形式配置多三方平台的附加器
 *
 * @author Force-oneself
 * @date 2021-08-06
 */
public class PropertiesOpenConfigStorageAdder implements OpenConfigStorageAdder {

    private final WxOpenProperties properties;

    public PropertiesOpenConfigStorageAdder(WxOpenProperties properties) {
        this.properties = properties;
    }

    @Override
    public void accept(List<WxOpenConfigStorage> configStorages) {
        if (!properties.isEnabled()) {
            return;
        }
        configStorages.add(configStorageForm(properties));
        if (CollectionUtils.isEmpty(properties.getMultiple())) {
            return;
        }
        configStorages.addAll(properties.getMultiple()
                .stream()
                .map(this::configStorageForm)
                .collect(Collectors.toSet()));
    }

    protected WxOpenInMemoryConfigStorage configStorageForm(WxProperties prop) {
        prop.verification(WxOpenProperties.PREFIX);
        WxOpenInMemoryConfigStorage multi = new WxOpenInMemoryConfigStorage();
        multi.setComponentAppId(StringUtils.trimToNull(prop.getAppId()));
        multi.setComponentAppSecret(StringUtils.trimToNull(prop.getSecret()));
        multi.setComponentAesKey(StringUtils.trimToNull(prop.getAesKey()));
        multi.setComponentToken(StringUtils.trimToNull(prop.getToken()));
        return multi;
    }
}
