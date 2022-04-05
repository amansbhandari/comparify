package ca.dal.comparify.store.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

class StoreModelTest {
    @Test
    void testConstructor() {
        StoreModel actualStoreModel = new StoreModel();
        actualStoreModel.setCity("Oxford");
        ObjectId getResult = ObjectId.get();
        actualStoreModel.setId(getResult);
        actualStoreModel.setPhone("4105551212");
        actualStoreModel.setStoreName("Store Name");
        actualStoreModel.setStreetName("Street Name");
        assertEquals("Oxford", actualStoreModel.getCity());
        assertSame(getResult, actualStoreModel.getId());
        assertEquals("4105551212", actualStoreModel.getPhone());
        assertEquals("Store Name", actualStoreModel.getStoreName());
        assertEquals("Street Name", actualStoreModel.getStreetName());
    }

    @Test
    void testConstructor2() {
        StoreModel actualStoreModel = new StoreModel(ObjectId.get(), "Store Name", "Street Name", "Oxford", "4105551212");
        actualStoreModel.setCity("Oxford");
        ObjectId getResult = ObjectId.get();
        actualStoreModel.setId(getResult);
        actualStoreModel.setPhone("4105551212");
        actualStoreModel.setStoreName("Store Name");
        actualStoreModel.setStreetName("Street Name");
        assertEquals("Oxford", actualStoreModel.getCity());
        assertSame(getResult, actualStoreModel.getId());
        assertEquals("4105551212", actualStoreModel.getPhone());
        assertEquals("Store Name", actualStoreModel.getStoreName());
        assertEquals("Street Name", actualStoreModel.getStreetName());
    }
}

