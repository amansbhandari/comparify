package ca.dal.comparify.item.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemRequestModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ItemRequestModel actualItemRequestModel = new ItemRequestModel();
        actualItemRequestModel.setDefaultImage("Default Image");
        actualItemRequestModel.setDescription("The characteristics of someone or something");
        actualItemRequestModel.setItemCategoryId("42");
        actualItemRequestModel.setName("Name");
        assertEquals("Default Image", actualItemRequestModel.getDefaultImage());
        assertEquals("The characteristics of someone or something", actualItemRequestModel.getDescription());
        assertEquals("42", actualItemRequestModel.getItemCategoryId());
        assertEquals("Name", actualItemRequestModel.getName());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        ItemRequestModel actualItemRequestModel = new ItemRequestModel("Name",
            "The characteristics of someone or something", "Default Image", "42");
        actualItemRequestModel.setDefaultImage("Default Image");
        actualItemRequestModel.setDescription("The characteristics of someone or something");
        actualItemRequestModel.setItemCategoryId("42");
        actualItemRequestModel.setName("Name");
        assertEquals("Default Image", actualItemRequestModel.getDefaultImage());
        assertEquals("The characteristics of someone or something", actualItemRequestModel.getDescription());
        assertEquals("42", actualItemRequestModel.getItemCategoryId());
        assertEquals("Name", actualItemRequestModel.getName());
    }
}

