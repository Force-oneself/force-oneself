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

import java.util.Arrays;
import java.util.List;

/**
 * @Description: class RedissonLogBeanPostProcessor
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
@Component
@ConditionalOnClass(RedissonClient.class)
@Slf4j
public class RedissonLogBeanPostProcessor implements BeanPostProcessor {

    /**
     * 过滤掉非法的方法：getConnectionManager，getCommandExecutor，getEvictionScheduler
     * 里边对象存在循环引用，会抛出StackOverflowError
     */
    public static final List<String> EXCLUDE_METHOD_LIST =
            Arrays.asList("getConnectionManager", "getCommandExecutor", "getEvictionScheduler");

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RedissonClient) {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.setProxyTargetClass(true);
            proxyFactory.addAdvice(new MethodBeforeAdviceInterceptor(
                    (method, args, target) -> log.info("redisson exec method: {}, args: {}", method.getName(),
                            Objs.prettyPrint(args))));
            proxyFactory.addAdvice(new AfterReturningAdviceInterceptor(
                    (retval, method, args, invo) -> log.info("redisson return : {}",
                            EXCLUDE_METHOD_LIST.contains(method.getName()) ? "null" : Objs.prettyPrint(retval))));
            return proxyFactory.getProxy();
        }
        return bean;
    }
}
