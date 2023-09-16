package com.quan.framework.amqp.advice;

import com.rabbitmq.client.Channel;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 * rabbit 手动ack拦截器
 *
 * @author Force-oneself
 * @date 2022-11-21
 */
public class RabbitManualAcknowledgeInterceptor implements MethodInterceptor {

    public static final Logger log = LoggerFactory.getLogger(RabbitManualAcknowledgeInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        Object object = arguments[1];
        if (!(object instanceof Message)) {
            // 正常执行不代理
            return invocation.proceed();
        }

        Channel channel = (Channel) arguments[0];
        long deliveryTag = ((Message) object).getMessageProperties().getDeliveryTag();
        long start = System.currentTimeMillis();
        try {
            Object result = invocation.proceed();
            channel.basicAck(deliveryTag, true);
            log.info("Rabbit 消息消费成功, result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Rabbit 消费失败, error", e);
            channel.basicNack(deliveryTag, false, true);
            return null;
        } finally {
            log.info("Rabbit Execution cost {}", System.currentTimeMillis() - start);
        }

    }
}
