package com.quan.framework.mongo.helper;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Force-oneself
 * @description MongoCollectionDelegate
 * @date 2022-01-17
 */
@SuppressWarnings("NullableProblems")
public class MongoCollectionDelegate<T> implements MongoCollection<T> {

    /**
     * 实际执行的代理类
     */
    private final MongoCollection<T> delegate;

    /**
     * 集合名
     */
    private final String collectionName;

    /**
     * class
     */
    private final Class<T> tDocumentClass;

    /**
     * 处理器
     */
    private final List<CollectionHandler> collectionHandlers;


    public MongoCollectionDelegate(MongoCollection<T> delegate, String collectionName, Class<T> tDocumentClass,
                                   List<CollectionHandler> handlers) {
        this.delegate = delegate;
        this.collectionName = collectionName;
        this.tDocumentClass = tDocumentClass;
        this.collectionHandlers = handlers == null ? new ArrayList<>() : handlers;
    }

    @Override
    public MongoNamespace getNamespace() {
        return delegate.getNamespace();
    }

    @Override
    public Class<T> getDocumentClass() {
        return delegate.getDocumentClass();
    }

    @Override
    public CodecRegistry getCodecRegistry() {
        return delegate.getCodecRegistry();
    }

    @Override
    public ReadPreference getReadPreference() {
        return delegate.getReadPreference();
    }

    @Override
    public WriteConcern getWriteConcern() {
        return delegate.getWriteConcern();
    }

    @Override
    public ReadConcern getReadConcern() {
        return delegate.getReadConcern();
    }

    @Override
    public <NewTDocument> MongoCollection<NewTDocument> withDocumentClass(Class<NewTDocument> clazz) {
        return delegate.withDocumentClass(clazz);
    }

    @Override
    public MongoCollection<T> withCodecRegistry(CodecRegistry codecRegistry) {
        return delegate.withCodecRegistry(codecRegistry);
    }

    @Override
    public MongoCollection<T> withReadPreference(ReadPreference readPreference) {
        return delegate.withReadPreference(readPreference);
    }

    @Override
    public MongoCollection<T> withWriteConcern(WriteConcern writeConcern) {
        return delegate.withWriteConcern(writeConcern);
    }

    @Override
    public MongoCollection<T> withReadConcern(ReadConcern readConcern) {
        return delegate.withReadConcern(readConcern);
    }

    @Override
    @Deprecated
    public long count() {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT));
        return delegate.count();
    }


    @Override
    @Deprecated
    public long count(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT).param(filter));
        return delegate.count(filter);
    }

    @Override
    @Deprecated
    public long count(Bson filter, CountOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT).param(filter).param(options));
        return delegate.count(filter, options);
    }

    @Override
    @Deprecated
    public long count(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT).param(clientSession));
        return delegate.count(clientSession);
    }

    @Override
    @Deprecated
    public long count(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT).param(clientSession).param(filter));
        return delegate.count(clientSession, filter);
    }

    @Override
    @Deprecated
    public long count(ClientSession clientSession, Bson filter, CountOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT).param(clientSession).param(filter).param(options));
        return delegate.count(clientSession, filter, options);
    }

    @Override
    public long countDocuments() {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS));
        return delegate.countDocuments();
    }

    @Override
    public long countDocuments(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS).param(filter));
        return delegate.countDocuments(filter);
    }

    @Override
    public long countDocuments(Bson filter, CountOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS).param(filter).param(options));
        return delegate.countDocuments(filter, options);
    }

    @Override
    public long countDocuments(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS).param(clientSession));
        return delegate.countDocuments(clientSession);
    }

    @Override
    public long countDocuments(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS).param(clientSession).param(filter));
        return delegate.countDocuments(clientSession, filter);
    }

    @Override
    public long countDocuments(ClientSession clientSession, Bson filter, CountOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.COUNT_DOCUMENTS).param(clientSession).param(filter).param(options));
        return delegate.countDocuments(clientSession, filter, options);
    }

    @Override
    public long estimatedDocumentCount() {
        this.handler(SimpleMethodOptions.of(MethodType.ESTIMATED_DOCUMENT_COUNT));
        return delegate.estimatedDocumentCount();
    }

    @Override
    public long estimatedDocumentCount(EstimatedDocumentCountOptions options) {
        return delegate.estimatedDocumentCount(options);
    }

    @Override
    public <TResult> DistinctIterable<TResult> distinct(String fieldName, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.DISTINCT).param(fieldName).param(tResultClass));
        return delegate.distinct(fieldName, tResultClass);
    }

    @Override
    public <TResult> DistinctIterable<TResult> distinct(String fieldName, Bson filter, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.DISTINCT).param(fieldName).param(filter).param(tResultClass));
        return delegate.distinct(fieldName, filter, tResultClass);
    }

    @Override
    public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName,
                                                        Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.DISTINCT).param(clientSession).param(fieldName).param(tResultClass));
        return delegate.distinct(clientSession, fieldName, tResultClass);
    }

    @Override
    public <TResult> DistinctIterable<TResult> distinct(ClientSession clientSession, String fieldName, Bson filter,
                                                        Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.DISTINCT).param(fieldName).param(filter).param(tResultClass));
        return delegate.distinct(clientSession, fieldName, filter, tResultClass);
    }

    @Override
    public FindIterable<T> find() {
        this.handler(SimpleMethodOptions.of(MethodType.FIND));
        return delegate.find();
    }

    @Override
    public <TResult> FindIterable<TResult> find(Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(tResultClass));
        return delegate.find(tResultClass);
    }

    @Override
    public FindIterable<T> find(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(filter));
        return delegate.find(filter);
    }

    @Override
    public <TResult> FindIterable<TResult> find(Bson filter, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(filter).param(tResultClass));
        return delegate.find(filter, tResultClass);
    }

    @Override
    public FindIterable<T> find(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(clientSession));
        return delegate.find(clientSession);
    }

    @Override
    public <TResult> FindIterable<TResult> find(ClientSession clientSession, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(clientSession).param(tResultClass));
        return delegate.find(clientSession, tResultClass);
    }

    @Override
    public FindIterable<T> find(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(clientSession).param(filter));
        return delegate.find(clientSession, filter);
    }

    @Override
    public <TResult> FindIterable<TResult> find(ClientSession clientSession, Bson filter, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND).param(clientSession).param(filter).param(tResultClass));
        return delegate.find(clientSession, filter, tResultClass);
    }

    @Override
    public AggregateIterable<T> aggregate(List<? extends Bson> pipeline) {
        this.handler(SimpleMethodOptions.of(MethodType.AGGREGATE).param(pipeline));
        return delegate.aggregate(pipeline);
    }

    @Override
    public <TResult> AggregateIterable<TResult> aggregate(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.AGGREGATE).param(pipeline).param(tResultClass));
        return delegate.aggregate(pipeline, tResultClass);
    }

    @Override
    public AggregateIterable<T> aggregate(ClientSession clientSession, List<? extends Bson> pipeline) {
        this.handler(SimpleMethodOptions.of(MethodType.AGGREGATE).param(clientSession).param(pipeline));
        return delegate.aggregate(clientSession, pipeline);
    }

    @Override
    public <TResult> AggregateIterable<TResult> aggregate(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.AGGREGATE).param(clientSession).param(pipeline).param(tResultClass));
        return delegate.aggregate(clientSession, pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<T> watch() {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH));
        return delegate.watch();
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(tResultClass));
        return delegate.watch(tResultClass);
    }

    @Override
    public ChangeStreamIterable<T> watch(List<? extends Bson> pipeline) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(pipeline));
        return delegate.watch(pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(tResultClass).param(tResultClass));
        return delegate.watch(pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<T> watch(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(clientSession));
        return delegate.watch(clientSession);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(tResultClass).param(tResultClass));
        return delegate.watch(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<T> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(clientSession).param(pipeline));
        return delegate.watch(clientSession, pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline,
                                                         Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.WATCH).param(clientSession).param(pipeline).param(tResultClass));
        return delegate.watch(clientSession, pipeline, tResultClass);
    }

    @Override
    public MapReduceIterable<T> mapReduce(String mapFunction, String reduceFunction) {
        this.handler(SimpleMethodOptions.of(MethodType.MAP_REDUCE).param(mapFunction).param(reduceFunction));
        return delegate.mapReduce(mapFunction, reduceFunction);
    }

    @Override
    public <TResult> MapReduceIterable<TResult> mapReduce(String mapFunction, String reduceFunction, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.MAP_REDUCE).param(mapFunction).param(reduceFunction).param(tResultClass));
        return delegate.mapReduce(mapFunction, reduceFunction, tResultClass);
    }

    @Override
    public MapReduceIterable<T> mapReduce(ClientSession clientSession, String mapFunction, String reduceFunction) {
        this.handler(SimpleMethodOptions.of(MethodType.MAP_REDUCE).param(clientSession).param(mapFunction).param(reduceFunction));
        return delegate.mapReduce(clientSession, mapFunction, reduceFunction);
    }

    @Override
    public <TResult> MapReduceIterable<TResult> mapReduce(ClientSession clientSession, String mapFunction,
                                                          String reduceFunction, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.MAP_REDUCE)
                .param(clientSession)
                .param(mapFunction)
                .param(reduceFunction)
                .param(tResultClass)
                .param(tResultClass));
        return delegate.mapReduce(clientSession, mapFunction, reduceFunction, tResultClass);
    }

    @Override
    public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends T>> requests) {
        this.handler(SimpleMethodOptions.of(MethodType.BULK_WRITE).param(requests));
        return delegate.bulkWrite(requests);
    }

    @Override
    public BulkWriteResult bulkWrite(List<? extends WriteModel<? extends T>> requests, BulkWriteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.BULK_WRITE).param(requests).param(options));
        return delegate.bulkWrite(requests, options);
    }

    @Override
    public BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends T>> requests) {
        this.handler(SimpleMethodOptions.of(MethodType.BULK_WRITE).param(clientSession).param(requests));
        return delegate.bulkWrite(clientSession, requests);
    }

    @Override
    public BulkWriteResult bulkWrite(ClientSession clientSession, List<? extends WriteModel<? extends T>> requests,
                                     BulkWriteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.BULK_WRITE).param(clientSession).param(requests).param(options));
        return delegate.bulkWrite(clientSession, requests, options);
    }

    @Override
    public void insertOne(T t) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_ONE).param(t));
        delegate.insertOne(t);
    }

    @Override
    public void insertOne(T t, InsertOneOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_ONE).param(t).param(options));
        delegate.insertOne(t, options);
    }

    @Override
    public void insertOne(ClientSession clientSession, T t) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_ONE).param(clientSession).param(t));
        delegate.insertOne(clientSession, t);
    }

    @Override
    public void insertOne(ClientSession clientSession, T t, InsertOneOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_ONE).param(clientSession).param(t).param(options));
        delegate.insertOne(clientSession, t, options);
    }

    @Override
    public void insertMany(List<? extends T> ts) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_MANY).param(ts));
        delegate.insertMany(ts);
    }

    @Override
    public void insertMany(List<? extends T> ts, InsertManyOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_MANY).param(ts).param(options));
        delegate.insertMany(ts, options);
    }

    @Override
    public void insertMany(ClientSession clientSession, List<? extends T> ts) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_MANY).param(clientSession).param(ts));
        delegate.insertMany(clientSession, ts);
    }

    @Override
    public void insertMany(ClientSession clientSession, List<? extends T> ts, InsertManyOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.INSERT_MANY).param(clientSession).param(ts).param(options));
        delegate.insertMany(clientSession, ts, options);
    }

    @Override
    public DeleteResult deleteOne(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_ONE).param(filter));
        return delegate.deleteOne(filter);
    }

    @Override
    public DeleteResult deleteOne(Bson filter, DeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_ONE).param(filter).param(options));
        return delegate.deleteOne(filter, options);
    }

    @Override
    public DeleteResult deleteOne(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_ONE).param(clientSession).param(filter));
        return delegate.deleteOne(clientSession, filter);
    }

    @Override
    public DeleteResult deleteOne(ClientSession clientSession, Bson filter, DeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_ONE).param(clientSession).param(filter).param(options));
        return delegate.deleteOne(clientSession, filter, options);
    }

    @Override
    public DeleteResult deleteMany(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_MANY).param(filter));
        return delegate.deleteMany(filter);
    }

    @Override
    public DeleteResult deleteMany(Bson filter, DeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_MANY).param(filter).param(options));
        return delegate.deleteMany(filter, options);
    }

    @Override
    public DeleteResult deleteMany(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_MANY).param(clientSession).param(filter));
        return delegate.deleteMany(clientSession, filter);
    }

    @Override
    public DeleteResult deleteMany(ClientSession clientSession, Bson filter, DeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.DELETE_MANY).param(clientSession).param(filter).param(options));
        return delegate.deleteMany(clientSession, filter, options);
    }

    @Override
    public UpdateResult replaceOne(Bson filter, T replacement) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE).param(filter).param(replacement));
        return delegate.replaceOne(filter, replacement);
    }

    @Override
    @Deprecated
    public UpdateResult replaceOne(Bson filter, T replacement, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE).param(filter).param(replacement).param(updateOptions));
        return delegate.replaceOne(filter, replacement, updateOptions);
    }

    @Override
    public UpdateResult replaceOne(Bson filter, T replacement, ReplaceOptions replaceOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE).param(filter).param(replacement).param(replaceOptions));
        return delegate.replaceOne(filter, replacement, replaceOptions);
    }

    @Override
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, T replacement) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE).param(clientSession).param(filter).param(replacement));
        return delegate.replaceOne(clientSession, filter, replacement);
    }

    @Override
    @Deprecated
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, T replacement, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE)
                .param(clientSession)
                .param(filter)
                .param(replacement)
                .param(updateOptions));
        return delegate.replaceOne(clientSession, filter, replacement, updateOptions);
    }

    @Override
    public UpdateResult replaceOne(ClientSession clientSession, Bson filter, T replacement, ReplaceOptions replaceOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.REPLACE_ONE)
                .param(clientSession).param(filter).param(replacement).param(replaceOptions));
        return delegate.replaceOne(clientSession, filter, replacement, replaceOptions);
    }

    @Override
    public UpdateResult updateOne(Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(filter).param(update));
        return delegate.updateOne(filter, update);
    }

    @Override
    public UpdateResult updateOne(Bson filter, Bson update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(filter).param(update).param(updateOptions));
        return delegate.updateOne(filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(clientSession).param(filter).param(update));
        return delegate.updateOne(clientSession, filter, update);
    }

    @Override
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE)
                .param(clientSession).param(filter).param(update).param(updateOptions));
        return delegate.updateOne(clientSession, filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateOne(Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(filter).param(update));
        return delegate.updateOne(filter, update);
    }

    @Override
    public UpdateResult updateOne(Bson filter, List<? extends Bson> update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(filter).param(update).param(updateOptions));
        return delegate.updateOne(filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE).param(clientSession).param(filter).param(update));
        return delegate.updateOne(clientSession, filter, update);
    }

    @Override
    public UpdateResult updateOne(ClientSession clientSession, Bson filter, List<? extends Bson> update,
                                  UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_ONE)
                .param(clientSession).param(filter).param(update).param(updateOptions));
        return delegate.updateOne(clientSession, filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateMany(Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(filter).param(update));
        return delegate.updateMany(filter, update);
    }

    @Override
    public UpdateResult updateMany(Bson filter, Bson update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(filter).param(update).param(updateOptions));
        return delegate.updateMany(filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(clientSession).param(filter).param(update));
        return delegate.updateMany(clientSession, filter, update);
    }

    @Override
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, Bson update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY)
                .param(clientSession).param(filter).param(update).param(updateOptions));
        return delegate.updateMany(clientSession, filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateMany(Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(filter).param(update));
        return delegate.updateMany(filter, update);
    }

    @Override
    public UpdateResult updateMany(Bson filter, List<? extends Bson> update, UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(filter).param(update).param(updateOptions));
        return delegate.updateMany(filter, update, updateOptions);
    }

    @Override
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY).param(clientSession).param(filter).param(update));
        return delegate.updateMany(clientSession, filter, update);
    }

    @Override
    public UpdateResult updateMany(ClientSession clientSession, Bson filter, List<? extends Bson> update,
                                   UpdateOptions updateOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.UPDATE_MANY)
                .param(clientSession).param(filter).param(update).param(updateOptions));
        return delegate.updateMany(clientSession, filter, update, updateOptions);
    }

    @Override
    @Nullable
    public T findOneAndDelete(Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_DELETE).param(filter));
        return delegate.findOneAndDelete(filter);
    }

    @Override
    @Nullable
    public T findOneAndDelete(Bson filter, FindOneAndDeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_DELETE).param(filter).param(options));
        return delegate.findOneAndDelete(filter, options);
    }

    @Override
    @Nullable
    public T findOneAndDelete(ClientSession clientSession, Bson filter) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_DELETE).param(clientSession).param(filter));
        return delegate.findOneAndDelete(clientSession, filter);
    }

    @Override
    @Nullable
    public T findOneAndDelete(ClientSession clientSession, Bson filter, FindOneAndDeleteOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_DELETE).param(clientSession).param(filter).param(options));
        return delegate.findOneAndDelete(clientSession, filter, options);
    }

    @Override
    @Nullable
    public T findOneAndReplace(Bson filter, T replacement) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_REPLACE).param(filter).param(replacement));
        return delegate.findOneAndReplace(filter, replacement);
    }

    @Override
    @Nullable
    public T findOneAndReplace(Bson filter, T replacement, FindOneAndReplaceOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_REPLACE)
                .param(filter).param(replacement).param(options));
        return delegate.findOneAndReplace(filter, replacement, options);
    }

    @Override
    @Nullable
    public T findOneAndReplace(ClientSession clientSession, Bson filter, T replacement) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_REPLACE)
                .param(clientSession).param(filter).param(replacement));
        return delegate.findOneAndReplace(clientSession, filter, replacement);
    }

    @Override
    @Nullable
    public T findOneAndReplace(ClientSession clientSession, Bson filter, T replacement,
                               FindOneAndReplaceOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_REPLACE)
                .param(clientSession).param(filter).param(replacement).param(options));
        return delegate.findOneAndReplace(clientSession, filter, replacement, options);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE).param(filter).param(update));
        return delegate.findOneAndUpdate(filter, update);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(Bson filter, Bson update, FindOneAndUpdateOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE).param(filter).param(update).param(options));
        return delegate.findOneAndUpdate(filter, update, options);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE)
                .param(clientSession).param(filter).param(update));
        return delegate.findOneAndUpdate(clientSession, filter, update);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(ClientSession clientSession, Bson filter, Bson update, FindOneAndUpdateOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE)
                .param(clientSession).param(filter).param(update).param(options));
        return delegate.findOneAndUpdate(clientSession, filter, update, options);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE).param(filter).param(update));
        return delegate.findOneAndUpdate(filter, update);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(Bson filter, List<? extends Bson> update, FindOneAndUpdateOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE).param(filter).param(update).param(options));
        return delegate.findOneAndUpdate(filter, update, options);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(ClientSession clientSession, Bson filter, List<? extends Bson> update) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE).param(clientSession).param(filter).param(update));
        return delegate.findOneAndUpdate(clientSession, filter, update);
    }

    @Override
    @Nullable
    public T findOneAndUpdate(ClientSession clientSession, Bson filter, List<? extends Bson> update,
                              FindOneAndUpdateOptions options) {
        this.handler(SimpleMethodOptions.of(MethodType.FIND_ONE_AND_UPDATE)
                .param(clientSession).param(filter).param(update).param(options));
        return delegate.findOneAndUpdate(clientSession, filter, update, options);
    }

    @Override
    public void drop() {
        this.handler(SimpleMethodOptions.of(MethodType.DROP));
        delegate.drop();
    }

    @Override
    public void drop(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP).param(clientSession));
        delegate.drop(clientSession);
    }

    @Override
    public String createIndex(Bson keys) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEX).param(keys));
        return delegate.createIndex(keys);
    }

    @Override
    public String createIndex(Bson keys, IndexOptions indexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEX).param(keys).param(indexOptions));
        return delegate.createIndex(keys, indexOptions);
    }

    @Override
    public String createIndex(ClientSession clientSession, Bson keys) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEX).param(clientSession).param(keys));
        return delegate.createIndex(clientSession, keys);
    }

    @Override
    public String createIndex(ClientSession clientSession, Bson keys, IndexOptions indexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEX).param(clientSession).param(keys).param(indexOptions));
        return delegate.createIndex(clientSession, keys, indexOptions);
    }

    @Override
    public List<String> createIndexes(List<IndexModel> indexes) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEXES).param(indexes));
        return delegate.createIndexes(indexes);
    }

    @Override
    public List<String> createIndexes(List<IndexModel> indexes, CreateIndexOptions createIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEXES).param(indexes).param(createIndexOptions));
        return delegate.createIndexes(indexes, createIndexOptions);
    }

    @Override
    public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEXES).param(clientSession).param(indexes));
        return delegate.createIndexes(clientSession, indexes);
    }

    @Override
    public List<String> createIndexes(ClientSession clientSession, List<IndexModel> indexes,
                                      CreateIndexOptions createIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.CREATE_INDEXES)
                .param(createIndexOptions).param(indexes).param(createIndexOptions));
        return delegate.createIndexes(clientSession, indexes, createIndexOptions);
    }

    @Override
    public ListIndexesIterable<Document> listIndexes() {
        this.handler(SimpleMethodOptions.of(MethodType.LIST_INDEXES));
        return delegate.listIndexes();
    }

    @Override
    public <TResult> ListIndexesIterable<TResult> listIndexes(Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.LIST_INDEXES).param(tResultClass));
        return delegate.listIndexes(tResultClass);
    }

    @Override
    public ListIndexesIterable<Document> listIndexes(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.LIST_INDEXES).param(clientSession));
        return delegate.listIndexes(clientSession);
    }

    @Override
    public <TResult> ListIndexesIterable<TResult> listIndexes(ClientSession clientSession, Class<TResult> tResultClass) {
        this.handler(SimpleMethodOptions.of(MethodType.LIST_INDEXES).param(clientSession).param(tResultClass));
        return delegate.listIndexes(clientSession, tResultClass);
    }

    @Override
    public void dropIndex(String indexName) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(indexName));
        delegate.dropIndex(indexName);
    }

    @Override
    public void dropIndex(String indexName, DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(indexName).param(dropIndexOptions));
        delegate.dropIndex(indexName, dropIndexOptions);
    }

    @Override
    public void dropIndex(Bson keys) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(keys));
        delegate.dropIndex(keys);
    }

    @Override
    public void dropIndex(Bson keys, DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(keys).param(dropIndexOptions));
        delegate.dropIndex(keys, dropIndexOptions);
    }

    @Override
    public void dropIndex(ClientSession clientSession, String indexName) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(clientSession).param(indexName));
        delegate.dropIndex(clientSession, indexName);
    }

    @Override
    public void dropIndex(ClientSession clientSession, Bson keys) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(clientSession).param(keys));
        delegate.dropIndex(clientSession, keys);
    }

    @Override
    public void dropIndex(ClientSession clientSession, String indexName, DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX)
                .param(clientSession).param(indexName).param(dropIndexOptions));
        delegate.dropIndex(clientSession, indexName, dropIndexOptions);
    }

    @Override
    public void dropIndex(ClientSession clientSession, Bson keys, DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEX).param(clientSession).param(keys).param(dropIndexOptions));
        delegate.dropIndex(clientSession, keys, dropIndexOptions);
    }

    @Override
    public void dropIndexes() {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEXES));
        delegate.dropIndexes();
    }

    @Override
    public void dropIndexes(ClientSession clientSession) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEXES).param(clientSession));
        delegate.dropIndexes(clientSession);
    }

    @Override
    public void dropIndexes(DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEXES).param(dropIndexOptions));
        delegate.dropIndexes(dropIndexOptions);
    }

    @Override
    public void dropIndexes(ClientSession clientSession, DropIndexOptions dropIndexOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.DROP_INDEXES).param(clientSession).param(dropIndexOptions));
        delegate.dropIndexes(clientSession, dropIndexOptions);
    }

    @Override
    public void renameCollection(MongoNamespace newCollectionNamespace) {
        this.handler(SimpleMethodOptions.of(MethodType.RENAME_COLLECTION).param(newCollectionNamespace));
        delegate.renameCollection(newCollectionNamespace);
    }

    @Override
    public void renameCollection(MongoNamespace newCollectionNamespace, RenameCollectionOptions renameCollectionOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.RENAME_COLLECTION)
                .param(newCollectionNamespace).param(renameCollectionOptions));
        delegate.renameCollection(newCollectionNamespace, renameCollectionOptions);
    }

    @Override
    public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace) {
        this.handler(SimpleMethodOptions.of(MethodType.RENAME_COLLECTION).param(clientSession).param(newCollectionNamespace));
        delegate.renameCollection(clientSession, newCollectionNamespace);
    }

    @Override
    public void renameCollection(ClientSession clientSession, MongoNamespace newCollectionNamespace,
                                 RenameCollectionOptions renameCollectionOptions) {
        this.handler(SimpleMethodOptions.of(MethodType.RENAME_COLLECTION)
                .param(clientSession).param(newCollectionNamespace).param(renameCollectionOptions));
        delegate.renameCollection(clientSession, newCollectionNamespace, renameCollectionOptions);
    }

    private void handler(MethodOptions methodOptions) {
        SimpleCollectionHolder<T> holder = new SimpleCollectionHolder<>(collectionName, tDocumentClass, delegate,
                methodOptions);
        collectionHandlers.stream()
                .filter(handler -> handler.support(holder))
                .forEach(handler -> handler.handle(holder));
    }
}
