package com.quan.openfeign.fallback;

import com.quan.openfeign.fallback.ExceptionFallback;
import feign.Target;
import feign.hystrix.FallbackFactory;
import org.springframework.cglib.proxy.Enhancer;

/**
 * SimpleFallbackFactory
 *
 * @author Force-oneself
 * @date 2022-11-25
 */
public class SimpleFallbackFactory<T> implements FallbackFactory<T> {

    private final Target<T> target;

    public SimpleFallbackFactory(Target<T> target) {
        this.target = target;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new ExceptionFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }
}
