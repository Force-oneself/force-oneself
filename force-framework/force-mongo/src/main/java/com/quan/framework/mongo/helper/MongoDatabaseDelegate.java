package com.quan.framework.mongo.helper;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * @author Force-oneself
 * @description MongoDatabaseDelegate
 * @date 2022-01-17
 */
@SuppressWarnings("NullableProblems")
public class MongoDatabaseDelegate implements MongoDatabase {

    private final MongoDatabase delegate;

    public MongoDatabaseDelegate(MongoDatabase delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return delegate.getName();
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
    public MongoDatabase withCodecRegistry(CodecRegistry codecRegistry) {
        return delegate.withCodecRegistry(codecRegistry);
    }

    @Override
    public MongoDatabase withReadPreference(ReadPreference readPreference) {
        return delegate.withReadPreference(readPreference);
    }

    @Override
    public MongoDatabase withWriteConcern(WriteConcern writeConcern) {
        return delegate.withWriteConcern(writeConcern);
    }

    @Override
    public MongoDatabase withReadConcern(ReadConcern readConcern) {
        return delegate.withReadConcern(readConcern);
    }

    @Override
    public MongoCollection<Document> getCollection(String collectionName) {
        return new MongoCollectionDelegate<>(delegate.getCollection(collectionName), collectionName, Document.class);
    }

    @Override
    public <TDocument> MongoCollection<TDocument> getCollection(String collectionName, Class<TDocument> tDocumentClass) {
        return new MongoCollectionDelegate<>(delegate.getCollection(collectionName, tDocumentClass),
                collectionName, tDocumentClass);
    }

    @Override
    public Document runCommand(Bson command) {
        return delegate.runCommand(command);
    }

    @Override
    public Document runCommand(Bson command, ReadPreference readPreference) {
        return delegate.runCommand(command, readPreference);
    }

    @Override
    public <TResult> TResult runCommand(Bson command, Class<TResult> tResultClass) {
        return delegate.runCommand(command, tResultClass);
    }

    @Override
    public <TResult> TResult runCommand(Bson command, ReadPreference readPreference, Class<TResult> tResultClass) {
        return delegate.runCommand(command, readPreference, tResultClass);
    }

    @Override
    public Document runCommand(ClientSession clientSession, Bson command) {
        return delegate.runCommand(clientSession, command);
    }

    @Override
    public Document runCommand(ClientSession clientSession, Bson command, ReadPreference readPreference) {
        return delegate.runCommand(clientSession, command, readPreference);
    }

    @Override
    public <TResult> TResult runCommand(ClientSession clientSession, Bson command, Class<TResult> tResultClass) {
        return delegate.runCommand(clientSession, command, tResultClass);
    }

    @Override
    public <TResult> TResult runCommand(ClientSession clientSession, Bson command, ReadPreference readPreference, Class<TResult> tResultClass) {
        return delegate.runCommand(clientSession, command, readPreference, tResultClass);
    }

    @Override
    public void drop() {
        delegate.drop();
    }

    @Override
    public void drop(ClientSession clientSession) {
        delegate.drop(clientSession);
    }

    @Override
    public MongoIterable<String> listCollectionNames() {
        return delegate.listCollectionNames();
    }

    @Override
    public ListCollectionsIterable<Document> listCollections() {
        return delegate.listCollections();
    }

    @Override
    public <TResult> ListCollectionsIterable<TResult> listCollections(Class<TResult> tResultClass) {
        return delegate.listCollections(tResultClass);
    }

    @Override
    public MongoIterable<String> listCollectionNames(ClientSession clientSession) {
        return delegate.listCollectionNames(clientSession);
    }

    @Override
    public ListCollectionsIterable<Document> listCollections(ClientSession clientSession) {
        return delegate.listCollections(clientSession);
    }

    @Override
    public <TResult> ListCollectionsIterable<TResult> listCollections(ClientSession clientSession, Class<TResult> tResultClass) {
        return delegate.listCollections(clientSession, tResultClass);
    }

    @Override
    public void createCollection(String collectionName) {
        delegate.createCollection(collectionName);
    }

    @Override
    public void createCollection(String collectionName, CreateCollectionOptions createCollectionOptions) {
        delegate.createCollection(collectionName, createCollectionOptions);
    }

    @Override
    public void createCollection(ClientSession clientSession, String collectionName) {
        delegate.createCollection(clientSession, collectionName);
    }

    @Override
    public void createCollection(ClientSession clientSession, String collectionName, CreateCollectionOptions createCollectionOptions) {
        delegate.createCollection(clientSession, collectionName, createCollectionOptions);
    }

    @Override
    public void createView(String viewName, String viewOn, List<? extends Bson> pipeline) {
        delegate.createView(viewName, viewOn, pipeline);
    }

    @Override
    public void createView(String viewName, String viewOn, List<? extends Bson> pipeline, CreateViewOptions createViewOptions) {
        delegate.createView(viewName, viewOn, pipeline, createViewOptions);
    }

    @Override
    public void createView(ClientSession clientSession, String viewName, String viewOn, List<? extends Bson> pipeline) {
        delegate.createView(clientSession, viewName, viewOn, pipeline);
    }

    @Override
    public void createView(ClientSession clientSession, String viewName, String viewOn, List<? extends Bson> pipeline, CreateViewOptions createViewOptions) {
        delegate.createView(clientSession, viewName, viewOn, pipeline, createViewOptions);
    }

    @Override
    public ChangeStreamIterable<Document> watch() {
        return delegate.watch();
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
        return delegate.watch(tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        return delegate.watch(pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return delegate.watch(pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        return delegate.watch(clientSession);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> tResultClass) {
        return delegate.watch(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        return delegate.watch(clientSession, pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return delegate.watch(clientSession, pipeline, tResultClass);
    }

    @Override
    public AggregateIterable<Document> aggregate(List<? extends Bson> pipeline) {
        return delegate.aggregate(pipeline);
    }

    @Override
    public <TResult> AggregateIterable<TResult> aggregate(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return delegate.aggregate(pipeline, tResultClass);
    }

    @Override
    public AggregateIterable<Document> aggregate(ClientSession clientSession, List<? extends Bson> pipeline) {
        return delegate.aggregate(clientSession, pipeline);
    }

    @Override
    public <TResult> AggregateIterable<TResult> aggregate(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        return delegate.aggregate(clientSession, pipeline, tResultClass);
    }

}
