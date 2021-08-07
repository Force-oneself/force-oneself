package com.quan.wx.open.api;

import me.chanjar.weixin.open.api.WxOpenService;

import java.util.function.Supplier;

/**
 * @author Force-oneself 三方服务接口工厂
 * @Description WxOpenServiceFactory.java
 * @date 2021-08-05
 */
public interface WxOpenServiceFactory extends Supplier<WxOpenService> {

}
