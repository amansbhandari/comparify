package ca.dal.comparify.CompareItems;


import ca.dal.comparify.compareitems.CompareItemRepository;
import ca.dal.comparify.compareitems.model.CompareItemsModel;
import ca.dal.comparify.mongo.MongoRepository;
import ca.dal.comparify.store.model.StoreModel;

import org.assertj.core.util.Arrays;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
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

//    @Test
//    void TestfetchCompare()
//    {
//       String date="02-02-2000";
//       String itemid="aghcbv";
//
//
//
//        Date date1=new Date();
//        CompareItemsModel compareItemsModel = new CompareItemsModel();
//        compareItemsModel.setProductId("dummydata");
//        compareItemsModel.setBrandId("dummydata");
//        compareItemsModel.setStoreId("dummydata");
//        compareItemsModel.setImageText("dummydata");
//        compareItemsModel.setUserId("dummydata");
//        compareItemsModel.setPrice(100.0);
//        compareItemsModel.setDateOfPurchase(date1);
//        List compareItems = new ArrayList<>();
//        compareItems.add(compareItemsModel);
//
//        when(mongoRepository.find( any(), any(), any())).thenReturn(compareItems);
//        assertEquals(compareItemRepository.fetchCompare(itemid,date).size(), compareItems.size());
//
//    }
//


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





