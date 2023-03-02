package com.quan.framework.spring.mvc.xss;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
@ConfigurationProperties(prefix = XssProperties.PREFIX)
public class XssProperties {

    public static final String PREFIX = "force.servlet.xss";

    private boolean enable = true;

    private List<String> skipUrls = new ArrayList<>();

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<String> getSkipUrls() {
        return skipUrls;
    }

    public void setSkipUrls(List<String> skipUrls) {
        this.skipUrls = skipUrls;
    }
}
