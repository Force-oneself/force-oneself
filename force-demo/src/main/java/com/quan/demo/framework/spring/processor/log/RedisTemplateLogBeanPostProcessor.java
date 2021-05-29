package com.quan.demo.framework.spring.processor.log;

import com.quan.demo.framework.spring.utils.Objs;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: class RedisTemplateLogBeanPostProcessor
 * @Author Force丶Oneself
 * @Date 2021-05-24
 **/
@Component
@ConditionalOnClass(RedisTemplate.class)
@Slf4j
public class RedisTemplateLogBeanPostProcessor implements BeanPostProcessor {

    public static final List<String> OPS_METHOD_ARRAY =
            Arrays.asList("opsForList", "opsForHash", "opsForValue", "opsForZSet", "opsForSet", "opsForGeo",
                    "opsForHyperLogLog");

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RedisTemplate) {
            return this.beanProxy((RedisTemplate<?, ?>) bean);
        }
        return bean;
    }

    private Object beanProxy(RedisTemplate<?, ?> template) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(template);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice((MethodInterceptor) this::opsProxy);
        //这里获取到针对template的代理对象，并返回
        return proxyFactory.getProxy();
    }

    private Object opsProxy(MethodInvocation invocation) throws Throwable {
        if (!checkMethod(invocation)) {
            return invocation.proceed();
        }
        //拦截opsFor
        ProxyFactory adviceProxy = new ProxyFactory(invocation.proceed());
        adviceProxy.setProxyTargetClass(false);
        adviceProxy.addAdvice(new MethodBeforeAdviceInterceptor(
                (method, args, target) -> log.info("redisTemplate exec method: {}, args: {}", method.getName(),
                        Objs.prettyPrint(args))));
        adviceProxy.addAdvice(new AfterReturningAdviceInterceptor(
                (retval, method, args, invo) -> log.info("redisson return : {}", Objs.prettyPrint(args))));
        return adviceProxy.getProxy();
    }

    private boolean checkMethod(MethodInvocation invocation) {
        return OPS_METHOD_ARRAY.contains(invocation.getMethod().getName());
    }
}
