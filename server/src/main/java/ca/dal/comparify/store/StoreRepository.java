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

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.in;

@Service
public class StoreRepository {
    public static final String STORE_COLLECTION = "store";

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
        if(result == -1 )
            return "No collection with name " + STORE_COLLECTION + " found";
        else if(result == -2)
            return "Exception in mongodb";
        else if(result == 0)
            return "success";

        return "Unknown Error";
    }

}