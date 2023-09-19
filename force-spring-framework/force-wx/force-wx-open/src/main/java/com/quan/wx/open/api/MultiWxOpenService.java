package com.quan.wx.open.api;

import com.quan.wx.open.api.base.MultiWxServiceRouter;
import me.chanjar.weixin.open.api.WxOpenService;

/**
 * 微信多三方平台支持
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public interface MultiWxOpenService extends MultiWxServiceRouter<WxOpenService> {

    /**
     * 对外提供注册service的能力
     *
     * @param name    注册的name
     * @param service 服务
     */
    void register(String name, WxOpenService service);

    /**
     * 清除服务缓存
     *
     * @return 成功与否
     */
    Boolean refresh();


    /**
     * 删除指定服务
     *
     * @param name 指定服务名称
     * @return 成功与否
     */
    WxOpenService remove(String name);
}
