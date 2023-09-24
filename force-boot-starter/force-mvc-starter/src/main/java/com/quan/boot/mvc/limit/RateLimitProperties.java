package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.constant.PropConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 * @author Force-oneself
 * @date 2023-09-24
 */
@ConfigurationProperties(prefix = PropConstant.LIMIT)
public class RateLimitProperties {

    /**
     * 限流路径配置
     */
    private Map<String, RateLimitPath> paths = new HashMap<>();

    public Map<String, RateLimitPath> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, RateLimitPath> paths) {
        this.paths = paths;
    }
}
