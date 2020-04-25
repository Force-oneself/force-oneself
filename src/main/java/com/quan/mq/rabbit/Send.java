package com.quan.mq.rabbit;

import com.quan.mq.rabbit.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

/**
 * RabbitMQ的消费者
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    /**
     * 入门第一个HelloWorld
     */
    public static void helloWorld() {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RabbitMQUtils.close(connection, channel);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Send.helloWorld();
        }
    }
}
