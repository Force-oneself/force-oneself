package com.quan.wx.open.api.base;

/**
 * @author Force-oneself 服务注册中心
 * @Description WxServiceRegistry.java
 * @date 2021-08-05
 */
public interface WxServiceRegistry<T> {

    /**
     * 注册
     *
     * @param name    名称
     * @param service 服务
     */
    void register(String name, T service);

    /**
     * 获取服务
     *
     * @param name key名称
     * @return 所需服务
     */
    T get(String name);

    /**
     * 清除所有服务
     */
    void clear();

    /**
     * 删除指定服务缓存
     *
     * @param name 服务名称
     * @return 被删除的服务
     */
    T remove(String name);
}
