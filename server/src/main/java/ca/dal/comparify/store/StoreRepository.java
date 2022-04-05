package ca.dal.comparify.store;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.model.StoreModel;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ca.dal.comparify.mongo.MongoUtils.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.in;

@Service
public class StoreRepository {
    public static final String STORE_COLLECTION = "store";
    public static final int ERROR_1 = -1;
    public static final int ERROR_2 = -2;
    public static final int SUCCESS = 0;

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @return
     *
     * @author Meghna Rupchandani
     */
    public List<StoreModel> getAll(){
        List<StoreModel> result = mongoRepository.find(STORE_COLLECTION, Filters.empty(), StoreModel.class);
        return result;
    }

    /**
     * @return
     *
     * @author Chanpreet Singh
     */
    public List<StoreModel> getSpecificStores(ArrayList storeList){
        List<String> storeListUnique = (List<String>) storeList.stream().distinct().collect(Collectors.toList());
        ArrayList objectIds = new ArrayList();
        for(String s:storeListUnique)
            objectIds.add(new ObjectId(s));
        Bson query = and(in("_id", objectIds));
        List<StoreModel> result = mongoRepository.find(STORE_COLLECTION, query, StoreModel.class);
        return result;
    }

    /**
     * @return
     *
     * @author Aman Singh BHandari
     */
    public String saveStore(StoreModel storeModel){
       return getResponseMessage(mongoRepository.insertOne(STORE_COLLECTION,storeModel, StoreModel.class));
    }

    public String getResponseMessage(int result)
    {
        if(result == ERROR_1)
            return "No collection with name " + STORE_COLLECTION + " found";
        else if(result == ERROR_2)
            return "Exception in mongodb";
        else if(result == SUCCESS)
            return "success";

        return "Unknown Error";
    }

    /**
     * @author Chanpreet Singh
     */
    public Long getStoreCount(){
        return mongoRepository.count(STORE_COLLECTION, Filters.empty());
    }

    /**
     * @param storeId
     * @author Harsh Shah
     * @return
     */
    public StoreModel findStoreById(String storeId) {
        return mongoRepository.findOne(STORE_COLLECTION, eq("_id", new ObjectId(storeId)), StoreModel.class);
    }
}