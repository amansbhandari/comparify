package ca.dal.comparify.store;

import ca.dal.comparify.store.model.StoreModel;
import ca.dal.comparify.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    /**
     * @author Meghna Rupchandani
     */
    public ArrayList<Map> getAllStores(){
        List<StoreModel> mongoResult = storeRepository.getAll();
        ArrayList<Map> result = new ArrayList<>();
        for(StoreModel each:mongoResult) {
            Map<String, String> eachObj = new HashMap<String, String>(){{
                put("id", each.getId().toString());
                put("storeName", each.getStoreName());
                put("streetName", each.getStreetName());
                put("city", each.getCity());
                put("phone", each.getPhone());
            }};
            result.add(eachObj);
        }
        return result;
    }
}
