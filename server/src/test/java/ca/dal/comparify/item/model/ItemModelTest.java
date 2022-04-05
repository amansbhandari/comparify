package ca.dal.comparify.item.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import ca.dal.comparify.model.AuditModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ItemModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        ItemModel actualItemModel = new ItemModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualItemModel.setAudit(createResult);
        actualItemModel.setDefaultImage("Default Image");
        actualItemModel.setId("42");
        assertSame(createResult, actualItemModel.getAudit());
        assertEquals("Default Image", actualItemModel.getDefaultImage());
        assertEquals("42", actualItemModel.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        ItemModel actualItemModel = new ItemModel("42", "Name", "The characteristics of someone or something",
            "Default Image", "42", AuditModel.create("Jan 1, 2020 8:00am GMT+0100"));
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualItemModel.setAudit(createResult);
        actualItemModel.setDefaultImage("Default Image");
        actualItemModel.setId("42");
        assertSame(createResult, actualItemModel.getAudit());
        assertEquals("Default Image", actualItemModel.getDefaultImage());
        assertEquals("42", actualItemModel.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        ItemModel actualItemModel = new ItemModel("Name", "The characteristics of someone or something", "Default Image",
            "42", createResult);

        assertSame(createResult, actualItemModel.getAudit());
        assertEquals("Name", actualItemModel.getName());
        assertEquals("Default Image", actualItemModel.getDefaultImage());
        assertEquals("42", actualItemModel.getItemCategoryId());
        assertEquals("The characteristics of someone or something", actualItemModel.getDescription());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate() {
        ItemModel actualCreateResult = ItemModel.create(
            new ItemRequestModel("Name", "The characteristics of someone or something", "Default Image", "42"),
            "Jan 1, 2020 8:00am GMT+0100");
        assertEquals("Name", actualCreateResult.getName());
        assertEquals("Default Image", actualCreateResult.getDefaultImage());
        assertEquals("42", actualCreateResult.getItemCategoryId());
        assertEquals("The characteristics of someone or something", actualCreateResult.getDescription());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreateResult.getAudit().getCreatedBy());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testCreate2() {
        ItemModel actualCreateResult = ItemModel.create(new ItemModel(), "Jan 1, 2020 8:00am GMT+0100");
        assertNull(actualCreateResult.getName());
        assertNull(actualCreateResult.getDefaultImage());
        assertNull(actualCreateResult.getItemCategoryId());
        assertNull(actualCreateResult.getDescription());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreateResult.getAudit().getCreatedBy());
    }
}

