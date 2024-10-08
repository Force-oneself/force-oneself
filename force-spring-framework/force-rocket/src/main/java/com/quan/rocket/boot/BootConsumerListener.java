package com.quan.rocket.boot;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @date 2024-09-30
 */
@RocketMQMessageListener(topic = "BootTopicTest", consumerGroup = "boot-consumer-group")
@Component
public class BootConsumerListener implements RocketMQListener<Object> {

    @Override
    public void onMessage(Object message) {
        System.out.println(message.toString());
    }
}
