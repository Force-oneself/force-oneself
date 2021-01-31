package com.quan.common.design.pattern.event.bus;

/**
 * Bus接口定义了EventBus的所有使用方法
 */
public interface Bus {

    /**
     * 将某个对象注册到bus上，从此之后该类就成为Subscriber了
     *
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 将某个对象从Bus上取消注册，取消注册之后就不会再接受到来自Bus的任何消息
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 提交Event到默认的Topic
     *
     * @param event
     */
    void post(Object event);

    /**
     * 提交Event到指定的Topic
     *
     * @param event
     * @param topic
     */
    void post(Object event, String topic);

    /**
     * 关闭该Bus
     */
    void close();

    /**
     * 返回Bus的名称标识
     */
    String getBusName();
}
