package com.quan.wx.open.api.impl;

import com.quan.wx.open.api.WxOpenServiceFactory;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;

/**
 * @author Force-oneself
 * @Description DefaultWxOpenServiceFactory.java
 * @date 2021-08-05
 */
public class DefaultWxOpenServiceFactory implements WxOpenServiceFactory {

    @Override
    public WxOpenService get() {
        return new WxOpenServiceImpl();
    }
}
