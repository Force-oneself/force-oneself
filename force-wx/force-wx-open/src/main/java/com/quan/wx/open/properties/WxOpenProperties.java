package com.quan.wx.open.properties;

import com.quan.wx.open.common.WxProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
* @Description WxOpenProperties.java
* @Author Force-oneself
* @Date 2021-08-06 12:38
*/
@ConfigurationProperties(prefix = WxOpenProperties.PREFIX)
public class WxOpenProperties extends WxProperties {

    public static final String PREFIX = "wx.open";

    /**
     * 公众平台开关
     */
    private boolean enabled = true;
    /**
     * 多个公众平台配置
     */
    private List<WxProperties> multiple;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<WxProperties> getMultiple() {
        return multiple;
    }

    public void setMultiple(List<WxProperties> multiple) {
        this.multiple = multiple;
    }
}
