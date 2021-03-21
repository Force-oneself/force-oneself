package com.quan.demo.rpc.consumer;


import com.quan.demo.rpc.framework.ProxyFactory;
import com.quan.demo.rpc.provider.api.HelloService;

/**
 * Created by Yuk on 2018/12/31.
 */
public class Consumer {

    public static void main(String[] args) {

        // 此处模拟spring容器
        HelloService service = ProxyFactory.getProxy(HelloService.class);
        String result = service.sayHello("yukang");
        System.out.println(result);

    }
}
