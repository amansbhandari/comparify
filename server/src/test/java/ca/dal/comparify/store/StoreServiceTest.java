package ca.dal.comparify.store;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.model.StoreModel;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * aman singh bhandari
 */
@SpringBootTest
@ActiveProfiles("test")
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;
    @MockBean
    private StoreRepository storeRepository;

    @Test
    void testGetAllStore()
    {
        StoreModel storeModel = new StoreModel();
        storeModel.setId(new ObjectId("6247c13f28da7f42a60913e2"));
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        List storeModels = new ArrayList<>();
        storeModels.add(storeModel);
        when(storeRepository.getAll()).thenReturn(storeModels);

        assertEquals(storeService.getAllStores().size(), storeModels.size());
    }

    @Test
    void getSpecificStoreDetailsTest()
    {
        StoreModel storeModel = new StoreModel();
        storeModel.setId(new ObjectId("6247c13f28da7f42a60913e2"));
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(storeModel.getId().toString(), new HashMap<String, Object>(){{
            put("storeName", storeModel.getStoreName());
            put("streetName", storeModel.getStreetName());
            put("city", storeModel.getCity());
            put("phone", storeModel.getPhone());
        }});

        ArrayList listIds = new ArrayList();
        listIds.add("6247c13f28da7f42a60913e2");
        listIds.add("6247c13f28da7f42a60913e2");
        listIds.add("6247c13f28da7f42a60913e2");
        listIds.add("6247c13f28da7f42a60913e2");

        List storeModels = new ArrayList<>();
        storeModels.add(storeModel);
        when(storeRepository.getSpecificStores(listIds)).thenReturn(storeModels);

        HashMap<String, Object> mapresult = (HashMap<String, Object>) storeService.getSpecificStoreDetails(listIds).get("6247c13f28da7f42a60913e2");
        assertEquals(mapresult.get("streetName"), "dummyStreet");
    }


    @Test
    void testSaveStore()
    {
        StoreModel storeModel = new StoreModel();
        storeModel.setId(new ObjectId("6247c13f28da7f42a60913e2"));
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        List storeModels = new ArrayList<>();
        storeModels.add(storeModel);
        when(storeRepository.saveStore(storeModel)).thenReturn("success");

        assertEquals(storeService.saveStore(storeModel), "success");
    }
}
