package com.quan.demo.rpc.provider;


import com.quan.demo.rpc.framework.URL;
import com.quan.demo.rpc.protocol.http.HttpServer;
import com.quan.demo.rpc.provider.api.HelloService;
import com.quan.demo.rpc.provider.impl.HelloServiceImpl;
import com.quan.demo.rpc.register.Register;

/**
 * Created by Yuk on 2018/12/31.
 */
public class Provider {

    public static void main(String[] args) {

        // 注册服务
        URL url = new URL("localhost",8080);
        Register.register(url, HelloService.class.getName(), HelloServiceImpl.class);

        // 启动tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
    }
}
