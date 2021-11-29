package com.quan.demo.framework.producer.api.adapter.store;

/**
 * @author Force-oneself
 * @description RabbitStorageAdapter
 * @date 2021-09-28
 **/
public interface RabbitStorageAdapter<T> extends StorageAdapter<T> {

    /**
     * 发送的队列名称
     *
     * @return 名称
     */
    String queueName();
}
