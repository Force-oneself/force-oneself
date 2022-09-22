package com.quan.wx.open;

import com.quan.wx.open.api.*;
import com.quan.wx.open.api.impl.*;
import com.quan.wx.open.processor.MultiWxOpenServiceFactoryProcessor;
import com.quan.wx.open.properties.WxOpenProperties;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @author Force-oneself 多平台自动配置
 * @Description MultiWxOpenServiceAutoConfiguration.java
 * @date 2021-08-05
 */
@Configuration
@EnableConfigurationProperties(WxOpenProperties.class)
@ConditionalOnProperty(prefix = "wx.open.multi", value = "enabled", havingValue = "true", matchIfMissing = true)
public class MultiWxOpenServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WxOpenServiceFactory wxOpenServiceFactory() {
        return new DefaultWxOpenServiceFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public WxOpenServiceRegistry multiWxOpenServiceRegistry() {
        return new MultiWxOpenServiceRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public MultiWxOpenService multiWxOpenService(MultiWxOpenServiceFactoryProcessor processor) {
        return new MultiWxOpenServiceImpl(processor);
    }

    @Bean
    @ConditionalOnMissingBean
    public WxOpenConfigStorageFactory<List<WxOpenConfigStorage>> multiWxOpenConfigStorageFactory(
            List<OpenConfigStorageAdder> adders) {
        return new MultiSourceWxOpenConfigStorage(adders);
    }

    @Bean
    public OpenConfigStorageAdder propertiesOpenConfigStorageAdder(WxOpenProperties properties) {
        return new PropertiesOpenConfigStorageAdder(properties);
    }
}
