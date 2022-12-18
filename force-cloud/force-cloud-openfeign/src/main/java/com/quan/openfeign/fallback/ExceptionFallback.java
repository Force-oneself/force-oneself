package com.quan.openfeign.fallback;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.*;

/**
 * SimpleFallback
 *
 * @author Force-oneself
 * @date 2022-11-25
 */

public class ExceptionFallback<T> implements MethodInterceptor {

    private final static Logger log = LoggerFactory.getLogger(ExceptionFallback.class);
    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;
    private final static String CODE = "code";

    public ExceptionFallback(Class<T> targetType, String targetName, Throwable cause) {
        this.targetType = targetType;
        this.targetName = targetName;
        this.cause = cause;
    }

    @Nullable
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String errorMessage = cause.getMessage();
        log.error(String.format("SimpleFallback:[%s.%s] serviceId:[%s]", targetType.getName(), method.getName(), targetName), cause);
        Class<?> returnType = method.getReturnType();
        // 集合类型反馈空集合
        if (List.class == returnType || Collection.class == returnType) {
            return Collections.emptyList();
        }
        if (Set.class == returnType) {
            return Collections.emptySet();
        }
        if (Map.class == returnType) {
            return Collections.emptyMap();
        }
        // 暂时不支持 flux，rx，异步等，返回值不是 R，直接返回 null。
        //if (R.class != returnType) {
        //    return null;
        //}
        // 非 FeignException
        if (!(cause instanceof FeignException)) {
            //return R.fail(ResultCode.INTERNAL_SERVER_ERROR, errorMessage);
            System.out.println("feign exception");
        }
        FeignException exception = (FeignException) cause;
        byte[] content = exception.content();
        // 如果返回的数据为空
        //if (ObjectUtil.isEmpty(content)) {
        //    return R.fail(ResultCode.INTERNAL_SERVER_ERROR, errorMessage);
        //}
        // 转换成 jsonNode 读取，因为直接转换，可能 对方放回的并 不是 R 的格式。
        //JsonNode resultNode = JsonUtil.readTree(content);
        // 判断是否 R 格式 返回体
        //if (resultNode.has(CODE)) {
        //    return JsonUtil.getInstance().convertValue(resultNode, R.class);
        //}
        return "R.fail(resultNode.toString())";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExceptionFallback<?> that = (ExceptionFallback<?>) o;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetType);
    }
}
