package com.quan.framework.mongo.builder;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author Force-oneself
 * @description BuilderDemo
 * @date 2022-01-15
 */
public class BuilderDemo {

    public static void main(String[] args) {
        Criteria criteria = Criterias.of()
                .gt("ee", null)
                .gt(false, "sss", new Object())
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null)
                .gt("ee", null).criteria();
        Query query = Queries.of(criteria)
                .exclude("ss", "ss")
                .query();
    }
}
