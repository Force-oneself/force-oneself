package com.quan.boot.mvc.log;

import com.quan.boot.mvc.constant.PropConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@ConfigurationProperties(prefix = PropConstant.LOG)
public class LoggerProperties {

    /**
     * 启用日志记录
     */
    private boolean enabled = true;

    /**
     * Servlet请求日志记录类型
     */
    private int level = (1 << 7) - 1;

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
}
