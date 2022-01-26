package com.quan.framework.mongo.helper;

import com.mongodb.ClientSessionOptions;
import com.mongodb.DB;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;

import java.util.List;

/**
 * @author Force-oneself
 * @description MongoDbFactoryDelegate
 * @date 2022-01-17
 */
@SuppressWarnings("NullableProblems")
public class MongoDbFactoryDelegate implements MongoDbFactory {

    private final MongoDbFactory delegate;
    private final List<CollectionHandler> collectionHandlers;

    public MongoDbFactoryDelegate(MongoDbFactory delegate, List<CollectionHandler> collectionHandlers) {
        this.delegate = delegate;
        this.collectionHandlers = collectionHandlers;
    }

    @Override
    public MongoDatabase getDb() throws DataAccessException {
        return new MongoDatabaseDelegate(this.delegate.getDb(), collectionHandlers);
    }

    @Override
    public MongoDatabase getDb(String dbName) throws DataAccessException {
        return new MongoDatabaseDelegate(this.delegate.getDb(dbName), collectionHandlers);
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return this.delegate.getExceptionTranslator();
    }

    @Override
    @Deprecated
    public DB getLegacyDb() {
        return this.delegate.getLegacyDb();
    }

    @Override
    public ClientSession getSession(ClientSessionOptions options) {
        return this.delegate.getSession(options);
    }

    @Override
    public MongoDbFactory withSession(ClientSession session) {
        return this.delegate.withSession(session);
    }
}
