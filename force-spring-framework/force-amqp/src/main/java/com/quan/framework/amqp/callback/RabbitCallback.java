package com.quan.framework.amqp.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.NonNull;

/**
 * RabbitCallback
 *
 * @author Force-oneself
 * @date 2022-11-21
 */
public interface RabbitCallback extends RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    Logger LOGGER = LoggerFactory.getLogger(RabbitCallback.class);

    /**
     * 确认消息是否到达Exchange
     */
    @Override
    default void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            LOGGER.info("<=== RabbitMQ消息发送成功: {}", correlationData);
            return;
        }
        LOGGER.error("<=== RabbitMQ消息发送失败: {}", cause);
    }

    /**
     * 确认消息是否到达Queue，消息发送失败触发
     */
    @Override
    default void returnedMessage(@NonNull Message message, int replyCode,
                                 @NonNull String replyText, @NonNull String exchange, @NonNull String routingKey) {
        LOGGER.error("消息丢失:exchange({}),route({}),replyCode({}),replyText({})", exchange, routingKey, replyCode, replyText);
    }
}
