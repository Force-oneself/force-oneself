package com.quan.framework.amqp.demo.routing;

import com.quan.framework.amqp.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @description EmitLogDirect
 * @date 2021-12-19
 */
public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public void route(String[] argv) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String severity = getSeverity(argv);
        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
    }


    private String getSeverity(String[] strings) {
        if (strings.length < 1)
            return "info";
        return strings[0];
    }

    private String getMessage(String[] strings) {
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings);
    }

    private String joinStrings(String[] strings) {
        int length = strings.length;
        if (length == 0) return "";
        if (length <= 1) return "";
        StringBuilder words = new StringBuilder(strings[1]);
        for (int i = 1 + 1; i < length; i++) {
            words.append(" ").append(strings[i]);
        }
        return words.toString();
    }

}
