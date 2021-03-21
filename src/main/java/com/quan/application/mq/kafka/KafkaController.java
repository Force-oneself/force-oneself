package com.quan.application.mq.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-17
 **/
@RestController
public class KafkaController {

    @Autowired(required = false)
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("sendMsgWithTopic")
    public String sendMsgWithTopic(@RequestParam String topic, @RequestParam int partition, @RequestParam String key,
                                   @RequestParam String value) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, key, value);
        return "success";
    }
}
