package com.quan.rocket.boot;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author Force-oneself
 * @date 2024-09-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void bootProducer() {
        String topic = "BootTopicTest";
        // 同步消息
        rocketMQTemplate.syncSend(topic, "同步消息");

        // 异步消息
        rocketMQTemplate.asyncSend(topic, "异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功");
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("异步消息发送失败");
            }
        });

        // 单向消息
        rocketMQTemplate.sendOneWay(topic, "单向消息");

        // 延迟消息
        Message<String> delayMsg = MessageBuilder.withPayload("延迟消息").build();
        rocketMQTemplate.syncSend(topic, delayMsg, 3000, 2);

        // 顺序消息
        Arrays.asList(
                new OrderlyMessage("1", "下单"),
                new OrderlyMessage("1", "短信"),
                new OrderlyMessage("1", "物流"),
                new OrderlyMessage("2", "下单"),
                new OrderlyMessage("2", "短信"),
                new OrderlyMessage("2", "物流")
        ).forEach(msg -> rocketMQTemplate.syncSendOrderly(topic, msg, msg.getKey()));

        // 带tag消息
        rocketMQTemplate.syncSend(topic+":tagA", "带tag消息");

        // 带key消息
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload("带key消息")
                .setHeader(RocketMQHeaders.KEYS, "key")
                .build()
        );
    }

    private static class OrderlyMessage {

        private String key;
        private String body;

        public OrderlyMessage(String key, String body) {
            this.key = key;
            this.body = body;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
