package com.quan.framework.mongo.spring;

import com.quan.common.util.IdWorker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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
        mongoTemplate.insert(new MongoEntity(idWorker.nextId(), "Peter" + idWorker.nextId(), 34, "篮球"));
    }


    public void find() {
        Query query = new Query();
        mongoTemplate.find(query, MongoEntity.class);
        mongoTemplate.query(MongoEntity.class).distinct("name").all();
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
