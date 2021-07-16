package com.quan.framework.mongo.demo;

import com.quan.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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

    @Document("mongo")
    public static class MongoTest {

        public MongoTest(Long id, String name, Integer age, String hobby, String _class) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.hobby = hobby;
            this._class = _class;
        }

        public MongoTest() {}

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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String get_class() {
            return _class;
        }

        public void set_class(String _class) {
            this._class = _class;
        }
    }
}
