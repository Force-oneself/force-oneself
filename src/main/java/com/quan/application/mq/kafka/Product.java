package com.quan.application.mq.kafka;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-03-17
 **/
@Component
public class Product {

    @Autowired(required = false)
    private KafkaTemplate kafkaTemplate;

    public void send(String name) {
        User user = new User();
        user.setAge(23);
        user.setName(name);
        user.setSex("男");
        kafkaTemplate.send("user", JSON.toJSON(user));
    }

}
