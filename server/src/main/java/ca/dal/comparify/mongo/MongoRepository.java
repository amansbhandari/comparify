package ca.dal.comparify.mongo;


import ca.dal.comparify.utils.ObjectUtils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoRepository {

    private MongoDatabase database;

    @Autowired
    protected MongoRepository(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName){
       this.database = mongoClient.getDatabase(databaseName);
    }


    public <T> List<T> find(String collectionName, Bson query, Class<T> classOf){
        MongoCollection<Document> collection = this.database.getCollection(collectionName);

        List<T> output = new ArrayList<T>();

        collection.find(query)
                .allowDiskUse(true)
                .iterator()
                .forEachRemaining(result -> output.add(ObjectUtils.read(result.toJson(), classOf)));


        return output;

    }

}
