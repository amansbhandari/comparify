package ca.dal.comparify.brand.model;

import ca.dal.comparify.model.AuditModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class BrandModelTest {
    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor() {
        BrandModel actualBrandModel = new BrandModel();
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualBrandModel.setAudit(createResult);
        actualBrandModel.setId("42");
        assertSame(createResult, actualBrandModel.getAudit());
        assertEquals("42", actualBrandModel.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor2() {
        BrandModel actualBrandModel = new BrandModel("42", "Name", "The characteristics of someone or something",
            AuditModel.create("Jan 1, 2020 8:00am GMT+0100"));
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        actualBrandModel.setAudit(createResult);
        actualBrandModel.setId("42");
        assertSame(createResult, actualBrandModel.getAudit());
        assertEquals("42", actualBrandModel.getId());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testConstructor3() {
        AuditModel createResult = AuditModel.create("Jan 1, 2020 8:00am GMT+0100");
        BrandModel actualBrandModel = new BrandModel("Name", "The characteristics of someone or something", createResult);

        assertSame(createResult, actualBrandModel.getAudit());
        assertEquals("Name", actualBrandModel.getName());
        assertEquals("The characteristics of someone or something", actualBrandModel.getDescription());
    }

    /**
     * @author Harsh Shah
     */
    @Test
    void testTransform() {
        BrandModel actualTransformResult = BrandModel.transform(
            new BrandRequestModel("Name", "The characteristics of someone or something"), "Jan 1, 2020 8:00am GMT+0100");
        assertEquals("Name", actualTransformResult.getName());
        assertEquals("The characteristics of someone or something", actualTransformResult.getDescription());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualTransformResult.getAudit().getCreatedBy());
    }
}

