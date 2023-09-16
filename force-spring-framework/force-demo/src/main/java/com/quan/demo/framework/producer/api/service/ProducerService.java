package com.quan.demo.framework.producer.api.service;


import com.quan.demo.framework.producer.api.context.Context;
import com.quan.demo.framework.producer.api.util.ProducerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * @author Force-oneself
 * @description GroupPushManager
 * @date 2021-09-27
 **/
public interface ProducerService<T> {

    Logger log = LoggerFactory.getLogger(ProducerService.class);

    /**
     * 生产群推对象
     *
     * @param context 全局上下文
     */
    default void produce(Context<T> context) {
        try {
            prepareContext(context);
            IteratorService<T> iterator = iterator(context);
            FilterService<T> filter = filter(context);
            StorageService<T> store = storage(context);
            this.doProduce(context, iterator, filter, store);
        } catch (Throwable throwable) {
            log.error("推送异常", throwable);
        } finally {
            destroy(context);
        }
    }

    /**
     * 执行生产对象
     *
     * @param context  上下文
     * @param iterator 迭代器
     * @param filter   过滤器
     * @param store    存储器
     */
    default void doProduce(Context<T> context, IteratorService<T> iterator, FilterService<T> filter, StorageService<T> store) {
        iterator.iterator(holder -> {
            log.debug("迭代数据:{}", holder);
            try {
                if (filter.filter(holder, context)) {
                    store.store(holder, context);
                }
            } catch (Exception e) {
                log.error("迭代异常" + holder, e);
            }
        });
        store.store(null, context);
    }

    /**
     * 销毁工作
     *
     * @param context 上下文
     */
    default void destroy(Context<T> context) {
    }

    /**
     * 上下文的处理
     *
     * @param context 全局上下文
     */
    default void prepareContext(Context<T> context) {
        if (context == null) {
            throw new RuntimeException("上下文不能为空");
        }
        Assert.isTrue(!CollectionUtils.isEmpty(context.iterators()), "迭代器不允许为空");
        Assert.isTrue(!CollectionUtils.isEmpty(context.storages()), "存储器不允许为空");
        Assert.isTrue(Objects.nonNull(context.filter()), "过滤器不允许为空");

        ProducerUtils.setContext(context);
    }

    /**
     * 存储服务
     *
     * @param context 上下文
     * @return 服务
     */
    StorageService<T> storage(Context<T> context);

    /**
     * 过滤器服务
     *
     * @param context 上下文
     * @return 服务
     */
    FilterService<T> filter(Context<T> context);

    /**
     * 迭代器服务
     *
     * @param context 上下文
     * @return 服务
     */
    IteratorService<T> iterator(Context<T> context);

}
