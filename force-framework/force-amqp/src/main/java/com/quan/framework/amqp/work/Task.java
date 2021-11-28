package com.quan.framework.amqp.work;

import com.quan.framework.amqp.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Force-oneself
 * @Description Task
 * @date 2021-11-21
 */
@Component
public class Task {

    private final RabbitTemplate rabbitTemplate;

    public Task(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String argv) throws IOException {

        String message = String.join(" ", argv);

        Channel channel = RabbitMQUtils.getChannel(rabbitTemplate);

        channel.basicPublish("", "hello", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
