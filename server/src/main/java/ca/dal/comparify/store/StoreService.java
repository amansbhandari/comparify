package ca.dal.comparify.store;

import ca.dal.comparify.store.model.StoreModel;
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
     * Sonar fix by Harsh Shah
     */
    public List<Map<String, String>> getAllStores() {
        List<StoreModel> mongoResult = storeRepository.getAll();
        List<Map<String, String>> result = new ArrayList<>();
        for (StoreModel each : mongoResult) {
            Map<String, String> eachObj = new HashMap<>();
            eachObj.put("id", each.getId().toString());
            eachObj.put("storeName", each.getStoreName());
            eachObj.put("streetName", each.getStreetName());
            eachObj.put("city", each.getCity());
            eachObj.put("phone", each.getPhone());
            result.add(eachObj);
        }
        return result;
    }

    /**
     * @author Chanpreet Singh
     * Sonar fix by Harsh Shah
     */
    public Map<String, Object> getSpecificStoreDetails(ArrayList<String> storeList) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<StoreModel> mongoResult = storeRepository.getSpecificStores(storeList);
        for (StoreModel each : mongoResult) {
            HashMap<String, Object> itr = new HashMap<>();
            itr.put("storeName", each.getStoreName());
            itr.put("streetName", each.getStreetName());
            itr.put("city", each.getCity());
            itr.put("phone", each.getPhone());
            result.put(each.getId().toString(), itr);
        }
        return result;
    }


    /**
     * @return
     * @author Aman Singh BHandari
     */
    public String saveStore(StoreModel storeModel) {
        return storeRepository.saveStore(storeModel);
    }


    /**
     * @param storeId
     * @return
     */
    public StoreModel findStoreById(String storeId) {
        return storeRepository.findStoreById(storeId);
    }
}