package ca.dal.comparify.store;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.model.StoreModel;
import com.mongodb.client.model.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreRepository {
    public static final String BRAND_COLLECTION = "store";

    @Autowired
    private MongoRepository mongoRepository;

    /**
     * @return
     *
     * @author Meghna Rupchandani
     */
    public List<StoreModel> getAll(){
        List<StoreModel> result = mongoRepository.find(BRAND_COLLECTION, Filters.empty(), StoreModel.class);
        return result;
    }
}