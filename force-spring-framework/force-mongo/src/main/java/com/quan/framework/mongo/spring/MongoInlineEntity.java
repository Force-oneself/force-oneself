package com.quan.framework.mongo.spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Force-oneself
 * @Description MongoInlineEntity
 * @date 2021-08-21
 */
@Document("inline")
public class MongoInlineEntity {

    @Id
    private String id;

    private List<MongoEntity> inline;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MongoEntity> getInline() {
        return inline;
    }

    public void setInline(List<MongoEntity> inline) {
        this.inline = inline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
