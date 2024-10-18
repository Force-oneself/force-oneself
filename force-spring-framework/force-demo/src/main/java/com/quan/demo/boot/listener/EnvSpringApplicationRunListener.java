package com.quan.demo.boot.listener;

import com.google.common.base.CaseFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Force-oneself
 * @date 2023-04-12
 */
public class EnvSpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    public EnvSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }


    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Class<?> mainApplicationClass = application.getMainApplicationClass();
        Assert.notNull(mainApplicationClass, "SpringApplication.run(Xxx.class, args) 中 Xxx.class未指定");
        String appName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, mainApplicationClass.getSimpleName());
        SpringApplicationName springApplicationName = AnnotatedElementUtils.findMergedAnnotation(mainApplicationClass,
                SpringApplicationName.class);
        if (springApplicationName != null) {
            appName = springApplicationName.prefix() + springApplicationName.name();
        }
        String[] activeProfiles = environment.getActiveProfiles();
        // 判断环境:dev、test、prod
        List<String> profiles = Arrays.asList(activeProfiles);
        // 当前使用
        List<String> activeProfileList = new ArrayList<>(profiles);
        String profile;
        if (activeProfileList.isEmpty()) {
            // 默认dev开发
            profile = "dev";
            activeProfileList.add(profile);
            application.setAdditionalProfiles(profile);
        } else if (activeProfileList.size() == 1) {
            profile = activeProfileList.get(0);
        } else {
            // 同时存在dev、test、prod环境时
            throw new RuntimeException(
                    "同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
        }

        String startJarPath = Objects.requireNonNull(mainApplicationClass.getResource("/"))
                .getPath()
                .split("!")[0];
        String activePros = StringUtils.arrayToCommaDelimitedString(activeProfileList.toArray());
        System.out.printf("----启动中，读取到的环境变量:[%s]，jar地址:[%s]----%n", activePros, startJarPath);

        Properties defaultProperties = getDefaultProperties(appName, profile);
        application.setDefaultProperties(defaultProperties);
        MutablePropertySources sources = environment.getPropertySources();
        Map<String, Object> defaultPropertiesMap = new HashMap<>();
        for (Object key : Collections.list(defaultProperties.propertyNames())) {
            defaultPropertiesMap.put((String) key, defaultProperties.get(key));
        }
        sources.addLast(new MapPropertySource("defaultProperties", defaultPropertiesMap));

        Properties props = getProperties(profile, appName);
        // Seata注册group格式
        setProperty(props, "seata.tx-service-group", appName.concat("-group"));
        setProperty(props, "seata.application-id", appName);
        setProperty(props, "seata.enabled", "true");
    }

    private static Properties getDefaultProperties(String appName, String profile) {
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("spring.main.allow-bean-definition-overriding", "true");
        defaultProperties.setProperty("spring.sleuth.sampler.percentage", "1.0");
        defaultProperties.setProperty("spring.cloud.alibaba.seata.tx-service-group", appName.concat("-group"));
        defaultProperties.setProperty("spring.cloud.nacos.config.file-extension", "yaml");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].data-id", "force.yaml");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].group", "DEFAULT_GROUP");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].refresh", "true");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].data-id",
                "force-" + profile + ".yaml");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].group", "DEFAULT_GROUP");
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].refresh", "true");
        defaultProperties.setProperty("spring.application.name", appName);
        return defaultProperties;
    }

    private static Properties getProperties(String profile, String appName) {
        Properties props = System.getProperties();
        // 基础配置
        // props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        props.setProperty("info.version", "AppConstant.APPLICATION_VERSION");
        props.setProperty("info.desc", appName);
        props.setProperty("file.encoding", StandardCharsets.UTF_8.name());

        String nacosMode = "nacos";
        String nacosAddr = "nacos";
        String nacosNameSpace = "nacos";
        String seataNacosGroup = "SEATA-GROUP";
        String seataNamespace = "seata";
        // 注册中心
        props.setProperty("spring.cloud.nacos.discovery.server-addr", nacosAddr);
        props.setProperty("spring.cloud.nacos.discovery.namespace", nacosNameSpace);
        // 配置中心
        props.setProperty("spring.cloud.nacos.config.server-addr", nacosAddr);
        props.setProperty("spring.cloud.nacos.config.namespace", nacosNameSpace);
        // Seata注册中心
        props.setProperty("seata.registry.type", nacosMode);
        props.setProperty("seata.registry.nacos.server-addr", nacosAddr);
        props.setProperty("seata.registry.nacos.namespace", seataNamespace);
        props.setProperty("seata.registry.nacos.group", seataNacosGroup);
        props.setProperty("seata.registry.nacos.application", "seata-server");
        // seata配置中心
        props.setProperty("seata.config.type", nacosMode);
        props.setProperty("seata.config.nacos.server-addr", nacosAddr);
        props.setProperty("seata.config.nacos.namespace", nacosNameSpace);
        props.setProperty("seata.config.nacos.group", seataNacosGroup);
        return props;
    }

    public static void setProperty(Properties props, String key, String value) {
        if (StringUtils.isEmpty(props.getProperty(key))) {
            props.setProperty(key, value);
        }
    }
}
