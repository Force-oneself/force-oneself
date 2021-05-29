package com.quan.demo.framework.spring.processor.log;

import com.quan.demo.framework.spring.utils.Objs;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @Description: class RedissonLogBeanPostProcessor
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
@Component
@ConditionalOnClass(RedissonClient.class)
@Slf4j
public class RedissonLogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RedissonClient) {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.setProxyTargetClass(true);
            proxyFactory.addAdvice(new MethodBeforeAdviceInterceptor(
                    (method, args, target) -> log.info("redisson exec method: {}, args: {}", method.getName(),
                            Objs.prettyPrint(args))));
            proxyFactory.addAdvice(new AfterReturningAdviceInterceptor(
                    (retval, method, args, invo) -> log.info("redisson return : {}", Objs.prettyPrint(retval))));
            return proxyFactory.getProxy();
        }
        return bean;
    }
}
