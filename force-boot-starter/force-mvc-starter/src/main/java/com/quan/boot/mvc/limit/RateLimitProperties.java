package com.quan.boot.mvc.limit;

import com.quan.boot.mvc.constant.PropConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<RateLimitPath> paths = new ArrayList<>();

    public List<RateLimitPath> getPaths() {
        return paths;
    }

    public void setPaths(List<RateLimitPath> paths) {
        this.paths = paths;
    }
}
