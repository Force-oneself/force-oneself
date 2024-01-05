package com.quan.demo.boot.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author Force-oneself
 * @date 2023-04-12
 */
//@Component
public class SystemPrintApplicationRunner implements ApplicationRunner {

    private final ConfigurableEnvironment environment;

    public SystemPrintApplicationRunner(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Properties props = System.getProperties();
        for(String key : props.stringPropertyNames()){
            String value = props.getProperty(key);
            System.out.printf("%s: %s%n", key, value);
        }
    }
}
