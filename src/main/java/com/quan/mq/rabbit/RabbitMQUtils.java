package com.quan.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    private final static String HOST_NAME;
    private final static int PORT;
    private final static String USER_NAME;
    private final static String PASSWORD;
    private final static String VIRTUAL_HOST;
    private final static ConnectionFactory FACTORY;

    static {
        HOST_NAME = "127.0.0.1";
        PORT = 5672;
        USER_NAME = "guest";
        PASSWORD = "guest";
        VIRTUAL_HOST = "/";
        FACTORY = new ConnectionFactory();
        //设置服务端地址（域名地址/ip）
        FACTORY.setHost(HOST_NAME);
        //设置服务器端口号
        FACTORY.setPort(PORT);
        //设置虚拟主机(相当于数据库中的库)
        FACTORY.setVirtualHost(VIRTUAL_HOST);
        //设置用户名
        FACTORY.setUsername(USER_NAME);
        //设置密码
        FACTORY.setPassword(PASSWORD);
    }

    /**
     * @param
     * @return com.rabbitmq.client.Connection
     * @Decription RabbitMQ 获取连接
     * @Author Force-Oneself
     * @Date 2020-04-25
     */
    public static Connection getConnection() {
        try {
            Connection connection = null;
            connection = FACTORY.newConnection();
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param connection
     * @param channel
     * @return void
     * @Decription RabbitMQ关闭连接
     * @Author Force-Oneself
     * @Date 2020-04-25
     */
    public static void close(Connection connection, Channel channel) {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
