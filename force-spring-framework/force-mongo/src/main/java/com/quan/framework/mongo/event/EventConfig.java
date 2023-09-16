package com.quan.framework.mongo.event;

import com.quan.framework.mongo.spring.MongoEntity;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description MyEvent
 * @date 2021-09-02
 */
@Component
public class EventConfig {

    @EventListener(AfterLoadEvent.class)
    public void afterLoadEvent(AfterLoadEvent<MongoEntity> event) {
        System.out.println(event);
    }

    @EventListener(AfterConvertEvent.class)
    public void afterConvertEvent(AfterConvertEvent<MongoEntity> event) {
        System.out.println(event);
    }


}
