package com.quan.wx.open.api.impl;

import com.quan.wx.open.api.WxOpenServiceFactory;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;

/**
 * 默认三方配置服务是实现
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public class DefaultWxOpenServiceFactory implements WxOpenServiceFactory {

    @Override
    public WxOpenService get() {
        return new WxOpenServiceImpl();
    }
}
