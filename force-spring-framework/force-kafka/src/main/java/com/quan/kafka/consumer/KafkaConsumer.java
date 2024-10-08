package com.quan.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @date 2024-10-04
 */
@Component
public class KafkaConsumer {


    @KafkaListener(topics = "test")
    public void receive(String message){
        System.err.println(message);
    }
}
