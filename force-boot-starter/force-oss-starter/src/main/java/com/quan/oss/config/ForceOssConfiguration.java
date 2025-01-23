package com.quan.oss.config;

import com.quan.oss.*;
import com.quan.oss.properties.OssProperties;
import com.quan.oss.properties.OssTenantProperties;
import com.quan.oss.support.DefaultOssSelector;
import com.quan.oss.support.OssServiceImpl;
import com.quan.oss.support.OssTenantSelector;
import com.quan.oss.support.SimpleOssClientRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OSS 自动装配配置
 *
 * @author Force-oneself
 * @date 2024-12-29
 */
@ConditionalOnProperty(prefix = OssProperties.PREFIX, value = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(OssProperties.class)
public class ForceOssConfiguration {

    private final List<OssClient> clients;

    public ForceOssConfiguration(List<OssClient> clients) {
        this.clients = clients;
    }

    @Bean
    @ConditionalOnMissingBean
    public OssService ossService(OssSelector selector) {
        return new OssServiceImpl(selector);
    }

    @Bean
    @ConditionalOnMissingBean
    public OssSelector ossSelector(OssClientRegistry registry, OssProperties properties) {
        return new DefaultOssSelector(registry, properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public OssClientRegistry ossClientRegistry(ObjectProvider<List<OssClientRegistryCustomizer>> configurationCustomizersProvider) {
        SimpleOssClientRegistry registry = new SimpleOssClientRegistry();
        List<OssClientRegistryCustomizer> customizers = configurationCustomizersProvider.getIfAvailable();
        clients.forEach(client -> registry.register(client.getClass().getSimpleName(), client));
        if (customizers != null) {
            customizers.forEach(customizer -> customizer.customize(registry));
        }
        return registry;
    }


    @ConditionalOnProperty(prefix = OssTenantProperties.PREFIX, value = "enable", havingValue = "true", matchIfMissing = true)
    @EnableConfigurationProperties(OssTenantProperties.class)
    @Configuration
    public static class OssTenantConfiguration {

        @Bean
        public OssSelector ossTenantSelector(OssClientRegistry registry, OssTenantProperties properties) {
            return new OssTenantSelector(registry, properties);
        }

        @Bean
        public OssClientRegistryCustomizer ossTenantClientRegistryCustomizer(OssTenantProperties properties) {
            return registry -> properties.getClients().forEach((tenant, clientKey) -> {
                OssClient ossClient = registry.get(clientKey);
                if (ossClient != null) {
                    registry.register(tenant, ossClient);
                }
            });
        }
    }
}
