package com.quan.framework.amqp.advice;

import org.aopalliance.aop.Advice;

/**
 * RabbitAdviceFactory
 *
 * @author Force-oneself
 * @date 2022-11-21
 */
@FunctionalInterface
public interface RabbitAdviceFactory {

    /**
     * 获取rabbit切面拦截器
     *
     * @return 切面
     */
    Advice getRabbitAdvice();
}
