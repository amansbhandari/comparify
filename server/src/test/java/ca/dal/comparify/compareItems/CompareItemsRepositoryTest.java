package ca.dal.comparify.compareItems;


import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.mongo.MongoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")

/**
 * @author Meghna Rupchandani
 */

public class CompareItemsRepositoryTest {

    @Autowired
    private CompareItemRepository compareItemRepository;

    @MockBean
    private MongoRepository mongoRepository;

    @Test
    void testgetSameItems() {
        Date date = new Date();
        CompareItemsModel compareItemsModel = new CompareItemsModel();
        compareItemsModel.setUserId("dummydata");
        compareItemsModel.setBrandId("dummydata");
        compareItemsModel.setDateOfPurchase(date);
        compareItemsModel.setPrice(10.0);
        List compareItems = new ArrayList<>();
        compareItems.add(compareItemsModel);

        when(mongoRepository.find( any(), any(), any())).thenReturn(compareItems);
        assertEquals(compareItemRepository.getSameItems(compareItemsModel).size(), compareItems.size());
    }


    @Test
    void testSaveCompareItems()
    {
        Date date=new Date();
        CompareItemsModel compareItemsModel=new CompareItemsModel();
        compareItemsModel.setProductId("dummydata");
        compareItemsModel.setBrandId("dummydata");
        compareItemsModel.setStoreId("dummydata");
        compareItemsModel.setImageText("dummydata");
        compareItemsModel.setUserId("dummydata");
        compareItemsModel.setPrice(100.0);
        compareItemsModel.setDateOfPurchase(date);

        when(mongoRepository.insertOne(any(),any(),any())).thenReturn(0);
        assertEquals(compareItemRepository.save(compareItemsModel), 0);
    }

    /**
     * @author Chanpreet Singh
     */
    @Test
    void TestfetchCompare()
    {
        Date date=new Date();
        CompareItemsModel compareItemsModel=new CompareItemsModel();
        compareItemsModel.setProductId("dummydata");
        compareItemsModel.setBrandId("dummydata");
        compareItemsModel.setStoreId("dummydata");
        compareItemsModel.setImageText("dummydata");
        compareItemsModel.setUserId("dummydata");
        compareItemsModel.setPrice(100.0);
        compareItemsModel.setDateOfPurchase(date);
        List result = new ArrayList();
        result.add(compareItemsModel);
        result.add(compareItemsModel);
        when(mongoRepository.find(any(), any(), any())).thenReturn(result);
        List<CompareItemsModel> fetchCompareResult = compareItemRepository.fetchCompare("123", "2000-01-01");
        assertEquals(fetchCompareResult, result);
    }

    /**
     * @author Chanpreet Singh
     */
    @Test
    void TestfetchCompare2()
    {
        Date date=new Date();
        CompareItemsModel compareItemsModel=new CompareItemsModel();
        compareItemsModel.setProductId("dummydata");
        compareItemsModel.setBrandId("dummydata");
        compareItemsModel.setStoreId("dummydata");
        compareItemsModel.setImageText("dummydata");
        compareItemsModel.setUserId("dummydata");
        compareItemsModel.setPrice(100.0);
        compareItemsModel.setDateOfPurchase(date);
        List result = new ArrayList();
        result.add(compareItemsModel);
        result.add(compareItemsModel);
        when(mongoRepository.find(any(), any(), any())).thenReturn(result);
        List<CompareItemsModel> fetchCompareResult = compareItemRepository.fetchCompare("123", null);
        assertEquals(fetchCompareResult, result);
    }

    @Test
    void TestUpdateItem()
    {
        Date date=new Date();
        CompareItemsModel compareItemsModel=new CompareItemsModel();
        compareItemsModel.setProductId("dummydata");
        compareItemsModel.setBrandId("dummydata");
        compareItemsModel.setStoreId("dummydata");
        compareItemsModel.setImageText("dummydata");
        compareItemsModel.setUserId("dummydata");
        compareItemsModel.setPrice(100.0);
        compareItemsModel.setDateOfPurchase(date);

        when(mongoRepository.insertOne(any(),any(),any())).thenReturn(0);
        assertEquals(compareItemRepository.updateItem(compareItemsModel), false);
    }
}