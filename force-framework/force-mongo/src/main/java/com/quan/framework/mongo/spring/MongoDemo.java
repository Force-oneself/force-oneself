package com.quan.framework.mongo.spring;

import com.quan.common.util.IdWorker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Force-oneself
 * @Description MongoDemo.java
 * @date 2021-06-09
 */
@Component
public class MongoDemo {

    private final MongoTemplate mongoTemplate;

    public MongoDemo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert() {
        IdWorker idWorker = new IdWorker();
//        mongoTemplate.insert(new MongoEntity(idWorker.nextId(), "Peter" + idWorker.nextId(), 34, "篮球"));

        List<MongoEntity> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            list.add(new MongoEntity(idWorker.nextId(), "Peter" + idWorker.nextId(), 34, "篮球"));
        }


        MongoInlineEntity inlineEntity = new MongoInlineEntity();
        inlineEntity.setName("内嵌数组" + Math.random());
        inlineEntity.setInline(list);
        mongoTemplate.insert(inlineEntity);
    }


    public void find() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(0).and("ss").exists(true).and("time").gt(new Date()));
        List<MongoEntity> entities = mongoTemplate.find(query, MongoEntity.class);
        System.out.println(entities);
    }

    public void aggregation() {
        ProjectionOperation projectionOperation = Aggregation.project("age", "name");
        // 条件
        MatchOperation matchOperation = Aggregation.match(Criteria.where("age").is(34));
        // 分组
        GroupOperation groupOperation = Aggregation.group("hobby").sum("age").as("count");


        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
        AggregationResults<MongoEntityAggregation> results = mongoTemplate.aggregate(aggregation, "mongo", MongoEntityAggregation.class);
        List<MongoEntityAggregation> mappedResults = results.getMappedResults();
    }

    public static class MongoEntityAggregation{

        private String id;

        private Long count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }

}
