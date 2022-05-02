package com.quan.rocket.service;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ProducerService
 *
 * @author Force-oneself
 * @date 2022-05-02
 */
@Service
public class ProducerService {

    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

    private DefaultMQProducer producer = null;

    @PostConstruct
    public void init() {
        producer = new DefaultMQProducer("producerGroupName");
        producer.setNamesrvAddr("nameServer");
        producer.setRetryTimesWhenSendFailed(3);
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("消费服务创建失败", e);
        }
    }

    public void send(String topic, String msg) {
        Message message = new Message(topic, "", "", msg.getBytes());
        try {
            producer.send(message);
        } catch (Exception e) {
            log.error("消息发送失败", e);
        }
    }

    @PreDestroy
    public void destroy() {
        if (producer != null) {
            producer.shutdown();
        }
    }
}
