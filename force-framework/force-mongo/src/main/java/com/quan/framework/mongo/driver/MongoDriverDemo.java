package com.quan.framework.mongo.driver;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Force-oneself
 * @Description MongoDriverDemo
 * @date 2021-08-21
 */
public class MongoDriverDemo {


    public static String uri = "mongodb+srv://root:root@force-oneself.rshao.mongodb.net/myFirstDatabase?" +
            "retryWrites=true&w=majority";

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Document doc = collection.find(Filters.eq("title", "driver")).first();
            if (doc == null) {
                return;
            }
            System.out.println(doc.toJson());
        }
    }

    private static MongoCollection<Document> getCollection(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("myFirstDatabase");
        return database.getCollection("driver");
    }

    public static void quickStart() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("myFirstDatabase").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<DriverEntity> collection = database.getCollection("driver", DriverEntity.class);
            DriverEntity movie = collection.find(Filters.eq("title", "Back to the Future")).first();
            System.out.println(movie);
        }
    }

    public static void findOne() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Bson projectionFields = Projections.fields(
                    Projections.include("title", "imdb"),
                    Projections.excludeId());
            Document doc = collection.find(Filters.eq("title", "The Room"))
                    .projection(projectionFields)
                    .sort(Sorts.descending("imdb.rating"))
                    .first();
            if (doc == null) {
                System.out.println("No results found.");
            } else {
                System.out.println(doc.toJson());
            }
        }
    }

    public static void findMany() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Bson projectionFields = Projections.fields(
                    Projections.include("title", "imdb"),
                    Projections.excludeId());
            try (MongoCursor<Document> cursor = collection.find(Filters.lt("runtime", 15))
                    .projection(projectionFields)
                    .sort(Sorts.descending("title"))
                    .iterator()) {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            }
        }
    }


    public static void insertOne() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            try {
                collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("title", "Ski Bloopers")
                        .append("genres", Arrays.asList("Documentary", "Comedy")));
                System.out.println("Success! Inserted document id: ");
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public static void insertMany() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            List<Document> movieList = Arrays.asList(
                    new Document().append("title", "Short Circuit 3"),
                    new Document().append("title", "The Lego Frozen Movie"));
            try {
                collection.insertMany(movieList);
                System.out.println("Inserted document ids: ");
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

    public static void updateOne() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoCollection<Document> collection = getCollection(mongoClient);
            Document query = new Document().append("title",  "Cool Runnings 2");
            Bson updates = Updates.combine(
                    Updates.set("runtime", 99),
                    Updates.addToSet("genres", "Sports"),
                    Updates.currentTimestamp("lastUpdated"));
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(query, updates, options);
                System.out.println("Modified document count: " + result.getModifiedCount());
                System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
        }
    }
}
