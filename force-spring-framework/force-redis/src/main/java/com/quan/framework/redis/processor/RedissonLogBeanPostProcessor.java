package com.quan.framework.redis.processor;

import com.quan.common.util.Objs;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: class RedissonLogBeanPostProcessor
 * @Author Force丶Oneself
 * @Date 2021-05-27
 **/
@Component
@ConditionalOnBean(RedissonClient.class)
public class RedissonLogBeanPostProcessor implements BeanPostProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 过滤掉非法的方法：getConnectionManager，getCommandExecutor，getEvictionScheduler
     * 里边对象存在循环引用，会抛出StackOverflowError
     */
    public static final List<String> EXCLUDE_METHOD_LIST =
            Arrays.asList("getConnectionManager", "getCommandExecutor", "getEvictionScheduler");

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof RedissonClient) {
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.setProxyTargetClass(false);
            proxyFactory.addAdvice(new MethodBeforeAdviceInterceptor((method, args, target) ->
                    log.info("redisson exec method: {}, args: {}", method.getName(), Objs.prettyPrint(args))));
            proxyFactory.addAdvice(new AfterReturningAdviceInterceptor((retval, method, args, invo) ->
                    log.info("redisson return : {}",
                            EXCLUDE_METHOD_LIST.contains(method.getName()) ? "null" : Objs.prettyPrint(retval))));
            return proxyFactory.getProxy();
        }
        return bean;
    }
}
