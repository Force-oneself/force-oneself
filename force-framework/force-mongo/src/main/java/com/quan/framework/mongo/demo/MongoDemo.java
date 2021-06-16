package com.quan.framework.mongo.demo;

import com.quan.common.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author Force-oneself
 * @Description MongoDemo.java
 * @date 2021-06-09
 */
@Component
public class MongoDemo {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void test() {
        IdWorker idWorker = new IdWorker();
        mongoTemplate.insert(new MongoTest(idWorker.nextId(), "Peter" + idWorker.nextId(), 34, "篮球", "null"));
    }


    public void find() {
        Query query = new Query();
        mongoTemplate.find(query, MongoTest.class);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Document("mongo")
    public static class MongoTest {
        @Id
        private Long id;

        @Field("name")
        private String name;

        @Field("age")
        private Integer age;

        @Field("hobby")
        private String hobby;

        @Field("_class")
        private String _class;
    }
}
