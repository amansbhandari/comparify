package ca.dal.comparify.store;

import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.model.StoreModel;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * aman singh bhandari
 */
@SpringBootTest
@ActiveProfiles("test")
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @MockBean
    private MongoRepository mongoRepository;

    @Test
    void getResponseMessageTestException()
    {
        assertEquals(storeRepository.getResponseMessage(-2),"Exception in mongodb");

    }

    @Test
    void getResponseMessageTest()
    {
        assertEquals(storeRepository.getResponseMessage(0),"success");

    }

    @Test
    void getResponseMessageTestNoCollection()
    {
        assertEquals(storeRepository.getResponseMessage(-1),"No collection with name store found");

    }


    @Test
    void testSaveStore()
    {
        StoreModel storeModel = new StoreModel();
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        when(mongoRepository.insertOne(any(),any(),any())).thenReturn(0);
        assertEquals(storeRepository.saveStore(storeModel), "success");
    }

    @Test
    void testgetAll()
    {
        StoreModel storeModel = new StoreModel();
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        List storeModels = new ArrayList<>();

        storeModels.add(storeModel);

        when(mongoRepository.find(any(),any(),any())).thenReturn(storeModels);
        assertEquals(storeRepository.getAll().size(), storeModels.size());
    }


    @Test
    void testGetSpecificStores()
    {

        StoreModel storeModel = new StoreModel();
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        List storeModels = new ArrayList<>();

        when(mongoRepository.find(any(),any(),any())).thenReturn(storeModels);

        ArrayList listStrings = new ArrayList();
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");


        storeModels.add(storeModel);

        assertEquals(storeRepository.getSpecificStores(listStrings).size(), storeModels.size());
    }


    @Test
    void testGetStoreCount()
    {

        StoreModel storeModel = new StoreModel();
        storeModel.setStoreName("dummyStore");
        storeModel.setCity("dummyCity");
        storeModel.setPhone("dummyPhone");
        storeModel.setStreetName("dummyStreet");

        List storeModels = new ArrayList<>();

        when(mongoRepository.count(any(),any())).thenReturn(new Long(2));

        ArrayList listStrings = new ArrayList();
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");
        listStrings.add("6247c13f28da7f42a60913e2");


        storeModels.add(storeModel);

        assertEquals(storeRepository.getStoreCount(), new Long(2));
    }
    
    /**
     * @author Chanpreet Singh
     */
    @Test
    void getResponseMessageTestUnknown()
    {
        assertEquals(storeRepository.getResponseMessage(-3),"Unknown Error");

    }

}
