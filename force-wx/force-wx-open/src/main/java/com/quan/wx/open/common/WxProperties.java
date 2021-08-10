package com.quan.wx.open.common;

import org.springframework.util.Assert;

import java.io.Serializable;

/**
* @Description WxProperties.java 配置公共基类
* @Author Force-oneself
* @Date 2021-08-05 22:45
*/
public class WxProperties implements Serializable {

    /**
     * spring bean name,不可重复
     */
    private String name;

    /**
     * 设置微信的appId
     */
    private String appId;

    /**
     * 设置微信的app secret
     */
    private String secret;

    /**
     * 设置微信的token
     */
    private String token;

    /**
     * 设置微信的EncodingAESKey
     */
    private String aesKey;


    public void verification(String prefix) {
        Assert.hasText(appId, prefix + ".app-id,必输");
        Assert.hasText(name, prefix + ".name,必输");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
