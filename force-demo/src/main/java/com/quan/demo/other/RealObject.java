package com.quan.demo.other;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-06
 **/
public class RealObject implements ProxyInterface {

    @Override
    public void method() {
        System.out.println("这是个真实对象");
    }
}
