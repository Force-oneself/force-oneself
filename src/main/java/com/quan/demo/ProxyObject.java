package com.quan.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-06
 **/
public class ProxyObject implements InvocationHandler {

    private Object target;

    public ProxyObject(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }


    public static void main(String[] args) {
        ProxyInterface proxy = new ProxyObject(new RealObject()).getProxy();
        proxy.method();
    }
}
