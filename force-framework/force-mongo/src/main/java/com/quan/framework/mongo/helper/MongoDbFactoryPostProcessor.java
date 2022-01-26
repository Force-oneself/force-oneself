package com.quan.framework.mongo.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Force-oneself
 * @description MongoDbFactoryPostProcessor
 * @date 2022-01-25
 */
@Component
public class MongoDbFactoryPostProcessor implements BeanPostProcessor {

    private final List<CollectionHandler> collectionHandlers;

    public MongoDbFactoryPostProcessor(List<CollectionHandler> collectionHandlers) {
        this.collectionHandlers = collectionHandlers;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MongoDbFactory) {
            return new MongoDbFactoryDelegate((MongoDbFactory) bean, collectionHandlers);
        }
        return bean;
    }
}
