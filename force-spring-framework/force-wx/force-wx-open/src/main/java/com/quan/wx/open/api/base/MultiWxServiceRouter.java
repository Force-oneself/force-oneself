package com.quan.wx.open.api.base;

/**
 * 多服务配置路由
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public interface MultiWxServiceRouter<T> {

    /**
     * 获取微信服务接口
     *
     * @param name key名称
     * @return 所需服务接口
     */
    T router(String name);
}
