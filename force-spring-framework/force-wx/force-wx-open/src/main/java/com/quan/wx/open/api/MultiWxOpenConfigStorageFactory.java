package com.quan.wx.open.api;

import me.chanjar.weixin.open.api.WxOpenConfigStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 支持三方平台多配置接口工厂
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public interface MultiWxOpenConfigStorageFactory extends WxOpenConfigStorageFactory<List<WxOpenConfigStorage>> {

    /**
     * 默认实现多平台配置的组装
     *
     * @return 配置集合
     */
    @Override
    default List<WxOpenConfigStorage> get() {
        List<WxOpenConfigStorage> configStorages = new ArrayList<>();
        endow(configStorages);
        return configStorages.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(WxOpenConfigStorage::getComponentAppId))),
                ArrayList::new));
    }

    /**
     * 交由子类去赋予实际对象
     *
     * @param configStorages 三方配置载体
     */
    void endow(List<WxOpenConfigStorage> configStorages);

}
