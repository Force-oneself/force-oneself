package com.quan.demo.framework.aop;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * EnhancerDemo
 *
 * @author Force-oneself
 * @date 2022-07-28
 */
public class EnhancerDemo {

    public static void test1() {
        //使用Enhancer来给某个类创建代理类，步骤
        //1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(Service1.class);
        /*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
        此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接口，实现了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("调用方法:" + method);
            //可以调用MethodProxy的invokeSuper调用被代理类的方法
            return methodProxy.invokeSuper(o, objects);
        });
        // 返回固定值
        enhancer.setCallback((FixedValue) () -> "路人甲");

        // 无操作
        enhancer.setCallback(NoOp.INSTANCE);

        //创建2个Callback
        Callback[] callbacks = {
                //这个用来拦截所有insert开头的方法
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    long starTime = System.nanoTime();
                    Object result = methodProxy.invokeSuper(o, objects);
                    long endTime = System.nanoTime();
                    System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
                    return result;
                },
                //下面这个用来拦截所有get开头的方法，返回固定值的
                (FixedValue) () -> "易兮科技"
        };
        //调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(method -> {
            //获取当前调用的方法的名称
            String methodName = method.getName();
            // insert 使用0位置的拦截器
            return methodName.startsWith("insert") ? 0 : 1;
        });

        // CallbackHelper的使用
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method, Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
        //下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixdValueCallback = (FixedValue) () -> "路人甲Java";
        CallbackHelper callbackHelper = new CallbackHelper(Service1.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixdValueCallback;
            }
        };
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        //4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 proxy = (Service1) enhancer.create();
        //5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }

    public static class Service1 {
        public void m1() {
            System.out.println("我是m1方法");
        }

        public void m2() {
            System.out.println("我是m2方法");
        }
    }
}
