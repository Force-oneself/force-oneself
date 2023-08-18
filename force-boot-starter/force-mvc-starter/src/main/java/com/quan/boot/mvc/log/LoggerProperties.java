package com.quan.boot.mvc.log;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@ConfigurationProperties(prefix = LoggerProperties.PREFIX)
public class LoggerProperties {

    public static final String PREFIX = "force.servlet.logger";

    /**
     * 启用日志记录
     */
    private boolean enabled = true;

    /**
     * Servlet请求日志记录类型
     */
    private int level = (1 << 7) - 1;

    /**
     * 是否请求和响应分步返回
     */
    private boolean step = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isStep() {
        return step;
    }

    public void setStep(boolean step) {
        this.step = step;
    }
}
