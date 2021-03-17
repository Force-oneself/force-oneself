package com.quan.application.mq.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-17
 **/
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return new NewTopic("user", 2, (short) 1);
    }


    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
        return (message, exception, consumer) -> {
            System.out.println("消费异常：" + message.getPayload());
            return null;
        };
    }
}
