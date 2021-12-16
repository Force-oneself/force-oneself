package com.quan.framework.amqp.work;

import com.quan.framework.amqp.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @Description Work
 * @date 2021-11-21
 */
@Component
public class Work {

    private final RabbitTemplate rabbitTemplate;


    private static final String TASK_QUEUE_NAME = "work.queue";

    public Work(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void consumer() throws IOException {

        Channel channel = RabbitMQUtils.getChannel(rabbitTemplate);
        // acknowledgment is covered below
        boolean autoAck = true;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(" [x] Done");
            }
        }, consumerTag -> {
        });

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }

}
