package com.quan.framework.amqp.advice;

import org.aopalliance.aop.Advice;

/**
 * DefaultRabbitManualAcknowledgeFactory
 *
 * @author Force-oneself
 * @date 2022-11-21
 */
public class DefaultRabbitManualAcknowledgeFactory implements RabbitAdviceFactory{

    @Override
    public Advice getRabbitAdvice() {
        return new RabbitManualAcknowledgeInterceptor();
    }
}
