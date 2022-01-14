package com.quan.framework.mongo.builder;

import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description QueryBuilder
 * @date 2022-01-14
 */
public class Queries {

    private final Query query;

    public static Queries of() {
        return new Queries();
    }

    public static Queries of(Criterias builder) {
        return new Queries(builder);
    }

    public static Queries of(Criteria criteria) {
        return new Queries(criteria);
    }

    public Queries(Query query) {
        this.query = query == null ? new Query() : query;
    }

    public Queries(Criteria criteria) {
        this(criteria == null ? new Query() : new Query(criteria));
    }

    public Queries(Criterias builder) {
        this(builder == null ? new Criteria() : builder.criteria());
    }

    public Queries() {
        this.query = new Query();
    }

    public Query query() {
        return this.query;
    }

    public Queries and(boolean cond, Consumer<Query> consumer) {
        if (cond) {
            consumer.accept(this.query);
        }
        return this;
    }

    public Queries skip(boolean cond, long skip) {
        return and(cond, q -> q.skip(skip));
    }

    public Queries skip(long skip) {
        return skip(true, skip);
    }

    public Queries limit(boolean cond, int limit) {
        return and(cond, q -> q.limit(limit));
    }

    public Queries limit(int limit) {
        return limit(true, limit);
    }

    public Queries withHint(boolean cond, Document hint) {
        return and(cond, q -> q.withHint(hint));
    }

    public Queries withHint(Document limit) {
        return withHint(true, limit);
    }

    public Queries with(boolean cond, Pageable pageable) {
        return and(cond, q -> q.with(pageable));
    }

    public Queries with(Pageable pageable) {
        return with(true, pageable);
    }

    public Queries with(boolean cond, Sort sort) {
        return and(cond, q -> q.with(sort));
    }

    public Queries with(Sort sort) {
        return with(true, sort);
    }

    public Queries include(String... fields) {
        if (fields != null && fields.length > 0) {
            Arrays.stream(fields).forEach(field -> this.query.fields().include(field));
        }
        return this;
    }

    public Queries exclude(String... fields) {
        if (fields != null && fields.length > 0) {
            Arrays.stream(fields).forEach(field -> this.query.fields().exclude(field));
        }
        return this;
    }

    public Queries slice(String key, int size) {
        this.query.fields().slice(key, size);
        return this;
    }

    public Queries slice(String key, int offset, int size) {
        this.query.fields().slice(key, offset, size);
        return this;
    }

    public Queries elemMatch(String key, Criteria elemMatchCriteria) {
        this.query.fields().elemMatch(key, elemMatchCriteria);
        return this;
    }

    public Queries position(String field, int value) {
        this.query.fields().position(field, value);
        return this;
    }
}
