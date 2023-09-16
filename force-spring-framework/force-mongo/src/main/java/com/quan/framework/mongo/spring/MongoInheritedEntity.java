package com.quan.framework.mongo.spring;

/**
 * @author Force-oneself
 * @Description MongoInheritedEntity.java
 * @date 2021-08-18
 */
public class MongoInheritedEntity extends MongoEntity{

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
