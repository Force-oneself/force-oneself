package com.quan.lock.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.quan.lock.properties.LockProperties.PREFIX;

/**
 * Lock 配置文件
 *
 * @author Force-oneself
 * @date 2024-12-26
 */
@ConfigurationProperties(prefix = PREFIX)
public class LockProperties {

    public static final String PREFIX = "force.lock";

    /**
     * 是否开启锁
     */
    private boolean enable = true;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
