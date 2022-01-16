package com.quan.framework.mongo.helper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.*;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.data.util.CloseableIterator;

import java.util.List;

/**
 * @author Force-oneself
 * @description MongoTemplatePlus
 * @date 2022-01-15
 */
@SuppressWarnings("NullableProblems")
public class MongoTemplatePlus extends MongoTemplate {


    public MongoTemplatePlus(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    public MongoTemplatePlus(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public MongoTemplatePlus(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }

    @Override
    protected <T> T doFindOne(String collectionName, Document query, Document fields, CursorPreparer preparer,
                              Class<T> entityClass) {
        query.append("tenantId", "2323");
        return super.doFindOne(collectionName, query, fields, preparer, entityClass);
    }

    @Override
    protected <T> List<T> doFind(String collectionName, Document query, Document fields, Class<T> entityClass) {
        return super.doFind(collectionName, query, fields, entityClass);
    }

    @Override
    protected <T> List<T> doFind(String collectionName, Document query, Document fields, Class<T> entityClass,
                                 CursorPreparer preparer) {
        return super.doFind(collectionName, query, fields, entityClass, preparer);
    }


    @Override
    protected <T> CloseableIterator<T> doStream(Query query, Class<?> entityType, String collectionName,
                                                Class<T> returnType) {
        return super.doStream(query, entityType, collectionName, returnType);
    }

    @Override
    protected void executeQuery(Query query, String collectionName, DocumentCallbackHandler documentCallbackHandler,
                                CursorPreparer preparer) {
        super.executeQuery(query, collectionName, documentCallbackHandler, preparer);
    }

    @Override
    public boolean exists(Query query, Class<?> entityClass, String collectionName) {
        return super.exists(query, entityClass, collectionName);
    }

    @Override
    public <T> List<T> findDistinct(Query query, String field, String collectionName, Class<?> entityClass,
                                    Class<T> resultClass) {
        return super.findDistinct(query, field, collectionName, entityClass, resultClass);
    }

    @Override
    public <T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass,
                               String collectionName) {
        return super.findAndModify(query, update, options, entityClass, collectionName);
    }

    @Override
    public <S, T> T findAndReplace(Query query, S replacement, FindAndReplaceOptions options, Class<S> entityType,
                                   String collectionName, Class<T> resultType) {
        return super.findAndReplace(query, replacement, options, entityType, collectionName, resultType);
    }

    @Override
    public <T> T findAndRemove(Query query, Class<T> entityClass, String collectionName) {
        return super.findAndRemove(query, entityClass, collectionName);
    }

    @Override
    public long count(Query query, Class<?> entityClass, String collectionName) {
        return super.count(query, entityClass, collectionName);
    }


    @Override
    protected UpdateResult doUpdate(String collectionName, Query query, UpdateDefinition update, Class<?> entityClass,
                                    boolean upsert, boolean multi) {
        return super.doUpdate(collectionName, query, update, entityClass, upsert, multi);
    }

    @Override
    protected <T> DeleteResult doRemove(String collectionName, Query query, Class<T> entityClass, boolean multi) {
        return super.doRemove(collectionName, query, entityClass, multi);
    }

    @Override
    public <T> List<T> mapReduce(Query query, Class<?> domainType, String inputCollectionName, String mapFunction,
                                 String reduceFunction, MapReduceOptions mapReduceOptions, Class<T> resultType) {
        return super.mapReduce(query, domainType, inputCollectionName, mapFunction, reduceFunction, mapReduceOptions, resultType);
    }


    @Override
    public <O> AggregationResults<O> aggregate(Aggregation aggregation, String collectionName, Class<O> outputType,
                                               AggregationOperationContext context) {
        return super.aggregate(aggregation, collectionName, outputType, context);
    }

    @Override
    protected <O> CloseableIterator<O> aggregateStream(Aggregation aggregation, String collectionName,
                                                       Class<O> outputType, AggregationOperationContext context) {
        return super.aggregateStream(aggregation, collectionName, outputType, context);
    }


    @Override
    public <T> T save(T objectToSave, String collectionName) {
        return super.save(objectToSave, collectionName);
    }
}
