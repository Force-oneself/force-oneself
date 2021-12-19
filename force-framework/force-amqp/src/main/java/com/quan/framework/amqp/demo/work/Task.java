package com.quan.framework.amqp.demo.work;

import com.quan.framework.amqp.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;

/**
 * @author Force-oneself
 * @Description Task
 * @date 2021-11-21
 */
public class Task {

    public void send(String argv) throws Exception {

        String message = String.join(" ", argv);

        Channel channel = RabbitMQUtils.getChannel();

        channel.basicPublish("", "hello", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
