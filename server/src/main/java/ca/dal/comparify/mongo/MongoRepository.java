package ca.dal.comparify.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Service
public class MongoRepository {

    private MongoDatabase database;

    private CodecRegistry pojoCodecRegistry;

    @Autowired
    protected MongoRepository(MongoClient mongoClient,
                              @Value("${spring.data.mongodb.database}") String databaseName) {
        this.database = mongoClient.getDatabase(databaseName);

        this.pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }

    /**
     * @param collectionName
     * @return
     */
    private MongoCollection<Document> getCollection(String collectionName) {
        return this.database.getCollection(collectionName);
    }

    /**
     * @param collectionName
     * @param classOf
     * @param <T>
     * @return
     */
    private <T> MongoCollection<T> getCollection(String collectionName, Class<T> classOf) {
        try {
            return this.database.getCollection(collectionName, classOf).withCodecRegistry(this.pojoCodecRegistry);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param collectionName
     * @param query
     * @param classOf
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collectionName, Bson query, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        List<T> output = new ArrayList<>();

        if (collection == null) {
            return output;
        }

        collection.find(query)
                .allowDiskUse(true)
                .iterator()
                .forEachRemaining(output::add);


        return output;
    }

    /**
     * @param collectionName
     * @param query
     * @param classOf
     * @param <T>
     * @return
     */
    public <T> T findOne(String collectionName, Bson query, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        if (collection == null) {
            return null;
        }

        return collection.find(query).first();
    }

    /**
     * @param collectionName
     * @param object
     * @param classOf
     * @param <T>
     * @return
     */
    public <T> boolean insert(String collectionName, T object, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        InsertOneResult result = null;
        if (collection == null) {
            return false;
        }


        try {
            result = collection.insertOne(object);
        } catch (MongoException ex) {
            result = null;
        }

        if (result == null) {
            return false;
        }

        return result.wasAcknowledged();
    }

    /**
     * @param collectionName
     * @return
     */
    public long count(String collectionName, Bson query) {
        MongoCollection<Document> collection = getCollection(collectionName);
        return collection.countDocuments(query);
    }
}