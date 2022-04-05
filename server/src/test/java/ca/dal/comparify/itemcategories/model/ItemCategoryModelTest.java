package ca.dal.comparify.itemcategories.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

class ItemCategoryModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ItemCategoryModel actualItemCategoryModel = new ItemCategoryModel();
        actualItemCategoryModel.setCategoryName("Category Name");
        ObjectId getResult = ObjectId.get();
        actualItemCategoryModel.setId(getResult);
        assertEquals("Category Name", actualItemCategoryModel.getCategoryName());
        assertSame(getResult, actualItemCategoryModel.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        ItemCategoryModel actualItemCategoryModel = new ItemCategoryModel(ObjectId.get(), "Store Name");
        actualItemCategoryModel.setCategoryName("Category Name");
        ObjectId getResult = ObjectId.get();
        actualItemCategoryModel.setId(getResult);
        assertEquals("Category Name", actualItemCategoryModel.getCategoryName());
        assertSame(getResult, actualItemCategoryModel.getId());
    }
}

