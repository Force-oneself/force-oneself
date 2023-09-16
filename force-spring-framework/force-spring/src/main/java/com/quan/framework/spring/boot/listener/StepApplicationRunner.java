package com.quan.framework.spring.boot.listener;

import com.quan.common.util.AtomicUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @date 2023-08-21
 */
@Component
public class StepApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        AtomicUtils.stop();
    }
}
