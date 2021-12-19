package com.quan.framework.amqp.demo.topics;

import com.quan.framework.amqp.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @description EmitLogTopics
 * @date 2021-12-19
 */
public class EmitLogTopics {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = getRouting(argv);
        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
    }

    private static String getRouting(String[] strings) {
        if (strings.length < 1)
            return "anonymous.info";
        return strings[0];
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings);
    }

    private static String joinStrings(String[] strings) {
        int length = strings.length;
        if (length == 0) return "";
        if (length < 1) return "";
        StringBuilder words = new StringBuilder(strings[1]);
        for (int i = 1 + 1; i < length; i++) {
            words.append(" ").append(strings[i]);
        }
        return words.toString();
    }
}
