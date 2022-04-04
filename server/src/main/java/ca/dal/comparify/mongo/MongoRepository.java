package ca.dal.comparify.mongo;

import ca.dal.comparify.model.HashModel;
import ca.dal.comparify.utils.ObjectUtils;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Updates.combine;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


/**
 * @author Harsh Shah
 */
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
     *
     * @author Harsh Shah
     */
    private MongoCollection<Document> getCollection(String collectionName) {
        try {
        return this.database.getCollection(collectionName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param collectionName
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
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
     *
     * @author Harsh Shah
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
     *
     * @author Harsh Shah
     */
    public <T> List<T> find(String collectionName, Bson query, PaginationOptions options, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        List<T> output = new ArrayList<>();

        if (collection == null) {
            return output;
        }

        collection.find(query)
            .sort(options.getSort())
            .allowDiskUse(true)
            .iterator()
            .forEachRemaining(output::add);


        return output;
    }


    /**
     * @param collectionName
     * @param query
     * @param projection
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> List<T> find(String collectionName, Bson query, Bson projection, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        List<T> output = new ArrayList<>();

        if (collection == null) {
            return output;
        }

        if (projection == null) {
            projection = new Document();
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
     * @param projection
     * @param options
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> List<T> find(String collectionName, Bson query, Bson projection, PaginationOptions options,
                            Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        List<T> output = new ArrayList<>();

        if (collection == null) {
            return output;
        }

        if (projection == null) {
            projection = new Document();
        }

        collection.find(query)
            .sort(options.getSort())
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
     *
     * @author Harsh Shah
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
     * @param query
     * @param options
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> T findOne(String collectionName, Bson query, PaginationOptions options, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        if (collection == null) {
            return null;
        }

        return collection.find(query).sort(options.getSort()).first();
    }

    /**
     * @param collectionName
     * @param query
     * @param projection
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> T findOne(String collectionName, Bson query, Bson projection, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        if (collection == null) {
            return null;
        }

        if (projection == null) {
            projection = new Document();
        }

        return collection.find(query).projection(projection).first();
    }

    /**
     * @param collectionName
     * @param query
     * @param projection
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> T findOne(String collectionName, Bson query, Bson projection,
                         PaginationOptions options, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        if (collection == null) {
            return null;
        }

        if (projection == null) {
            projection = new Document();
        }

        return collection.find(query).projection(projection).sort(options.getSort()).first();
    }

    /**
     * @param collectionName
     * @param object
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> int insertOne(String collectionName, T object, Class<T> classOf) {
        MongoCollection<T> collection = getCollection(collectionName, classOf);

        InsertOneResult result = null;

        if (null == collection) {
            return -1;
        }

        try {
            result = collection.insertOne(object);
        } catch (MongoException ex) {
            return -2;
        }

        return result.wasAcknowledged() ? 0 : -1;
    }

    /**
     * @param collectionName
     * @return
     *
     * @author Harsh Shah
     */
    public long count(String collectionName, Bson query) {
        MongoCollection<Document> collection = getCollection(collectionName);

        if(null == collection){
            return -1;
        }

        return collection.countDocuments(query);
    }


    /**
     * @param collectionName
     * @param query
     * @param update
     * @return
     *
     * @author Harsh Shah
     */
    public boolean updateOne(String collectionName, Bson query, HashModel update) {
        MongoCollection<Document> collection = getCollection(collectionName);

        if (collection == null) {
            return false;
        }

        Document params = new Document("$set", new Document(update));

        UpdateResult result = collection.updateOne(query, params);

        return result.wasAcknowledged();
    }


    /**
     * @param collectionName
     * @param query
     * @param values
     * @return
     *
     * @author Harsh Shah
     */
    public boolean updateOne(String collectionName, Bson query, Bson... values) {
        MongoCollection<Document> collection = getCollection(collectionName);

        if (collection == null) {
            return false;
        }

        if (values.length == 0) {
            return false;
        }

        UpdateResult result = collection.updateOne(query, combine(values));

        return result.wasAcknowledged();
    }


    /**
     * @param collectionName
     * @param query
     * @return
     *
     * @author Harsh Shah
     */
    public boolean deleteOne(String collectionName, Bson query) {
        MongoCollection<Document> collection = getCollection(collectionName);

        if (collection == null) {
            return false;
        }

        DeleteResult result = collection.deleteOne(query);

        return result.wasAcknowledged();
    }

    /**
     * @param collectionName
     * @param query
     * @return
     *
     * @author Harsh Shah
     */
    public boolean deleteMany(String collectionName, Bson query) {
        MongoCollection<Document> collection = getCollection(collectionName);

        if (collection == null) {
            return false;
        }

        DeleteResult result = collection.deleteMany(query);

        return result.wasAcknowledged();
    }

    /**
     * @param collectionName
     * @param pipeline
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> List<T> aggregate(String collectionName, List<Bson> pipeline, Class<T> classOf) {

        MongoCollection<T> collection = getCollection(collectionName, classOf);

        List<T> output = new ArrayList<>();

        if (collection == null) {
            return Collections.emptyList();
        }

         collection.aggregate(pipeline)
             .allowDiskUse(true)
             .iterator()
             .forEachRemaining(output::add);

        return output;
    }


    /**
     * @param collectionName
     * @param pipeline
     * @param classOf
     * @param <T>
     * @return
     *
     * @author Harsh Shah
     */
    public <T> T aggregateOne(String collectionName, List<Bson> pipeline, Class<T> classOf) {

        MongoCollection<Document> collection = getCollection(collectionName);

        if (collection == null) {
            return null;
        }

        return ObjectUtils.convert(collection.aggregate(pipeline)
            .allowDiskUse(true).first(), classOf);

    }
}