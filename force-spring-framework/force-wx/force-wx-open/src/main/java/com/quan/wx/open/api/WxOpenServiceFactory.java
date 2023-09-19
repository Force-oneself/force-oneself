package com.quan.wx.open.api;

import me.chanjar.weixin.open.api.WxOpenService;

import java.util.function.Supplier;

/**
 * 三方服务接口工厂
 *
 * @author Force-oneself
 * @date 2021-08-05
 */
public interface WxOpenServiceFactory extends Supplier<WxOpenService> {

}
