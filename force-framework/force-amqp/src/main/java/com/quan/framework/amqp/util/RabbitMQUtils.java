package com.quan.framework.amqp.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Force-oneself
 * @Description RabbitMQUtils
 * @date 2021-11-22
 */
public final class RabbitMQUtils {

    private final static Logger log = LoggerFactory.getLogger(RabbitMQUtils.class);

    public static Connection getConnection(RabbitTemplate rabbitTemplate) {
        Connection connection = rabbitTemplate.getConnectionFactory().createConnection().getDelegate();
        if (Objects.isNull(connection)) {
            throw new RuntimeException("连接失败");
        }
        return connection;
    }


    public static Channel getChannel(RabbitTemplate rabbitTemplate) {
        try {
            return getConnection(rabbitTemplate).createChannel();
        } catch (IOException e) {
            log.error("获取通道失败", e);
            throw new RuntimeException("获取通道失败");
        }
    }

}
