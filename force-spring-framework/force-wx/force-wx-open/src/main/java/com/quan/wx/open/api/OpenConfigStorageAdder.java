package com.quan.wx.open.api;

import me.chanjar.weixin.open.api.WxOpenConfigStorage;

import java.util.List;
import java.util.function.Consumer;

/**
 * 三方平台配置附加器
 *
 * @author Force-oneself
 * @date 2021-08-06
 */
public interface OpenConfigStorageAdder extends Consumer<List<WxOpenConfigStorage>> {

}
